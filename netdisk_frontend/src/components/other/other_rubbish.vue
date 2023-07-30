<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column width="350" prop="name" label="文件名称">
        <template slot-scope="scope">
          <div @click="viewDetail(scope.row)" style="cursor: pointer;">
            <i :class="scope.row.icon" style="font-size: larger;"></i>
            {{ scope.row.name }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="日期" width="240" sortable>
        <template slot-scope="scope">{{ scope.row.folderCreatetime }}</template>
      </el-table-column>
      <el-table-column label="大小" width="240" sortable>
        <template slot-scope="scope">{{ scope.row.fileSize }}</template>
      </el-table-column>
      <el-table-column label="文件类型" width="120">
        <template slot-scope="scope">{{ scope.row.type }}</template>
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <!-- <template slot="header">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
        </template> -->
        <template slot-scope="scope">
          <el-button size="small" @click="updatemg(scope.row)">取消删除</el-button>
          <el-popconfirm title="确定删除吗？" style="margin-left: 5px" @confirm="deletemg(scope.row)">
            <el-button slot="reference" size="small" type="danger">彻底删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>


    <!-- <el-dialog 
      :visible.sync="dialogVisible" 
      :lock-scroll="false">
      <el-upload class="upload-demo" 
          action="http://localhost:8088/file/upload" 
          :show-file-list="true"
          :on-success="handleSuccess" 
          :before-upload="beforeAvatarUpload">
          <el-button size="large" type="primary" style="border-radius: 30px;">上传</el-button>
        </el-upload>
    </el-dialog> -->
  </div>
</template>
  
<script>
export default {
  name: "other_rubbish",
  data() {
    return {
      tableData: [],
      belongUser: JSON.parse(sessionStorage.getItem("Userms")).userId,
    }
  },
  methods: {
    getFolders(user, state) {
      this.$axios.post('http://localhost:8088/folder/rufolders', {
        belongUser: user, isdel: state
      }).then(res => {
        console.log(res.data);
        for (const x of res.data) {
          let time = new Date();
          if (!x["type"]) {
            x["type"] = 'folder';
            x["name"] = x.folderName;
          }
          x["folderCreatetime"] = time.toLocaleString(x["folderCreatetime"]);
        }
        this.tableData.push(...res.data);

        for (const x of this.tableData) {
          x["type"] == "folder" ?
            x.icon = "el-icon-folder" : x.icon = "el-icon-document";
        }

      }).catch(error => {
        alert("出错啦！！！");
        console.log(error);
      })
    },
    getFiles(user, state) {
      this.$axios.post('http://localhost:8088/file/rufiles', {
        belongUser: user, isDel: state
      }).then(res => {
        console.log(res.data);
        for (const x of res.data) {
          let time = new Date();
          if (!x["type"]) {
            x["type"] = 'file';
            x["name"] = x.fileName;
          }
          x["folderCreatetime"] = time.toLocaleString(x["folderCreatetime"]);
        }
        this.tableData.push(...res.data);

        for (const x of this.tableData) {
          x["type"] == "folder" ? x.icon = "el-icon-folder" : x.icon = "el-icon-document";
        }

      }).catch(error => {
        alert("出错啦！！！");
        console.log(error);
      })
    },
    myPost(url, data) {
      this.$axios.post(url, data).then(res => {
        console.log(res);
      });
    },
    delayLoad() {
      setTimeout(() => {
        while (this.tableData.length) {
          this.tableData.pop();
        }
        this.loadGet();
      }, 1000)
    },
    updatemg(row) {
      if (row.type === "folder") {
        this.myPost('http://localhost:8088/folder/updatefolder', { folderId: row.folderId, isdel: 0 });
      } else {
        this.myPost('http://localhost:8088/file/updatefile', { userfileId: row.userfileId, isDel: 0 });
      }
      this.delayLoad();
    },
    deletemg(row) {
      if (row.type === "folder") {
        this.myPost('http://localhost:8088/folder/deletefolder', { folderId: row.folderId });
      } else {
        this.myPost('http://localhost:8088/file/deletefile', { userfileId: row.userfileId });
        console.log("执行这里: ", { userfileId: row.userfileId })
      }
      this.delayLoad();
    },
    loadGet() {
      this.belongUser = this.belongUser.toString();
      this.getFolders(this.belongUser, 1);
      this.getFiles(this.belongUser, 1);
      console.log("tableData:", this.tableData);
    }
  },
  beforeMount() {
    this.loadGet();
  }
}
</script>
  
<style scoped></style>
  