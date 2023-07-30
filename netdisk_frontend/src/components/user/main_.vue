<template>
  <div>
    <div style="margin-bottom: 5px; display: flex; ">
      <el-input v-model="uname" placeholder="请输入姓名" suffix-icon="el-icon-search" style="width: 200px"></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>
      <!-- <el-button type="primary" style="margin-left: 5px" @click="add">新增</el-button> -->
    </div>

    <el-table :border="true" :data="tableData" style="width: 100%">
      <el-table-column prop="userId" label="序号" width="50">
      </el-table-column>
      <el-table-column prop="userName" label="姓名" width="80">
      </el-table-column>
      <el-table-column prop="userEmail" label="邮箱" width="180">
      </el-table-column>
      <el-table-column prop="driveSize" label="空间大小" width="100">
      </el-table-column>
      <el-table-column prop="userNickname" label="网名" width="100">
      </el-table-column>
      <el-table-column prop="userImgurl" label="图片地址">
        <template slot-scope="scope">
          <div style="cursor: pointer;">
            {{ scope.row.userImgurl.length > 40 ? scope.row.userImgurl.substring(0, 40) + "..." : scope.row.userImgurl }}
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="operate" label="" align="right" width="200">
        <template slot-scope="scope">
          <el-button size="small" @click="updateUser(scope.row)">修改</el-button>
          <el-popconfirm title="确定删除吗？" style="margin-left: 5px" @confirm="deleteUser(scope.row)">
            <el-button slot="reference" size="small" type="danger">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="用户详情" :visible.sync="dialogFormVisible" :lock-scroll="false" width="30%">
      <el-form :model="form">
        <el-form-item label="用户名称:" :label-width="formLabelWidth">
          {{ form.userName }}
        </el-form-item>
        <el-form-item label="用户邮箱:" :label-width="formLabelWidth">
          {{ form.userEmail }}
        </el-form-item>
        <el-form-item label="空间大小:" :label-width="formLabelWidth">
          <el-select v-model="form.driveSize">
            <el-option label="51200 KMB" value="51200"></el-option>
            <el-option label="102400 KMB" value="102400"></el-option>
          </el-select>
          <!-- <el-input v-model="form.name" autocomplete="off" class="inputbox"></el-input> -->
        </el-form-item>
        <el-form-item label="用户权限:" :label-width="formLabelWidth">
          <el-select v-model="form.userLevel">
            <el-option label="管理员权限" value="1"></el-option>
            <el-option label="普通用户权限" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="网盘名称:" :label-width="formLabelWidth">
          {{ form.userEmail }}
        </el-form-item>
        <el-form-item label="头像地址:" :label-width="formLabelWidth">
          {{ form.userImgurl.length > 40 ? form.userImgurl.substring(0, 40) + "..." : form.userImgurl }}
        </el-form-item>
      </el-form>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="commitMsg">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "main_",
  data() {
    return {
      tableData: [],
      tableDataCopy: [],
      uname: '',
      formLabelWidth: '80px',
      dialogFormVisible: false,
      form: {
        userId: '',
        userName: '',
        userEmail: '',
        driveSize: '',
        userLevel: '',
        userNickname: '',
        userImgurl: ''
      },
      level: ''
    }
  },
  methods: {
    delayLoad() {
      setTimeout(() => {
        while (this.tableData.length) {
          this.tableData.pop();
        }
        this.loadGet();
      }, 1000)
    },
    loadGet() {
      this.$axios.get('http://localhost:8088/user/allusers').then((response) => {
        console.log(response);
        this.tableData.push(...response.data);
        // this.tableData = response.data;
        // for(const x in response.data){
        //     this.tableData.push(response.data[x])
        // }
        this.tableDataCopy.push(...this.tableData);
        // console.log('这里：tableDataCopy\n', this.tableDataCopy);
      }).catch(Error => {
        console.log(Error);
      })
    },
    loadPost() {
      if (this.uname === '') {
        this.tableData = this.tableDataCopy;
        // console.log(this.tableData);
        // console.log(this.tableDataCopy);
      } else {
        let data = [];
        data.push(...this.tableDataCopy);
        for (let x in data) {
          if ( !data[x].userName.includes( this.uname ) ) {
            data.splice(x, 1);
          }
        }
        // console.log('这里：tableDataCopy\n', this.tableDataCopy);
        // console.log('这里：data\n', data);
        this.tableData = data;
      }

    },
    resetParam() {
      this.uname = '';
    },
    add() {

    },
    updateUser(row) {
      console.log(row);
      this.dialogFormVisible = true;
      this.form = {
        userId: row.userId,
        userName: row.userName,
        userEmail: row.userEmail,
        driveSize: row.driveSize,
        userLevel: row.userLevel,
        userNickname: row.userNickname,
        userImgurl: row.userImgurl
      }
      this.level = this.form.userLevel === "1" ? "管理员" : "普通用户"
      // this.$axios.post('', {}).then((response) => {
      //   console.log(response);
      //   this.delayLoad();
      // }).catch(Error => {
      //   console.log(Error);
      // })
    },
    deleteUser(row) {
      this.axios.post('http://localhost:8088/user/deleteuser', {userId: row.userId}).then(this.delayLoad);
    },
    commitMsg() {
      this.dialogFormVisible = false;
      // console.log(this.form);
      this.$axios.post('http://localhost:8088/user/upuser', {
        userId: this.form.userId, userLevel: this.form.userLevel, driveSize: this.form.driveSize
      }).then(res => {
        console.log(res);

        this.delayLoad();
      });
    }
  },
  beforeMount() {
    this.loadGet();
  }
}
</script>

<style scoped>
.inputbox {
  width: 280px;
}
</style>
