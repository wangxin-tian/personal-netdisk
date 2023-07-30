<template>
  <div>
  <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        width="180"
        prop="name"
        label="文件名称">
        <template slot-scope="scope">
          <div @click="viewDetail(scope.row)" style="cursor: pointer;">
            <i :class="scope.row.icon"></i>
            {{scope.row.name}}
          </div>
        </template>
      </el-table-column>
      <el-table-column>
        <template slot="header" slot-scope="scope">
        <el-button
          size="mini"
          type="primary"
          @click="handleEdit(scope.$index, scope.row)">添加</el-button>
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">修改</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="日期"
        width="200">
        <template slot-scope="scope">{{ scope.row.folderCreatetime }}</template>
      </el-table-column>
      <el-table-column
        label="文件类型"
        width="120">
        <template slot-scope="scope">{{ scope.row.type }}</template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
    name: "main_folder",
    data() {
        return {
          tableData: [],
          belongUser: JSON.parse(sessionStorage.getItem("Userms")).userId,
        }
    },
    methods: {
        localGet() {
            this.belongUser = this.belongUser.toString();
            console.log(this.belongUser);
            // console.log(typeof this.belongUser);
            this.$axios.post('http://localhost:8088/folder/allfolders',this.belongUser).then(res=>{
                console.log(res.data);
                for(const x of res.data){
                  let time = new Date();
                  if(!x["type"]){
                    x["type"] = 'folder';
                    x["name"] = x.folderName;
                  }
                  x["folderCreatetime"] = time.toLocaleString(x["folderCreatetime"]);
                }
                this.tableData.push(...res.data);

                for(const x of this.tableData){
                  x["type"] == "folder" ? x.icon = "el-icon-folder" : x.icon = "el-icon-document";
                }
                
            })
            .catch(error=>{
                alert("出错啦！！！");
                console.log(error);
            })

            this.$axios.get('http://localhost:8088/userfile').then(res=>{
                console.log(res.data);
                for(const x of res.data){
                  let time = new Date();
                  if(!x["type"]){
                    x["type"] = 'file';
                    x["name"] = x.fileName;
                  }
                  x["folderCreatetime"] = time.toLocaleString(x["folderCreatetime"]);
                }
                this.tableData.push(...res.data);

                for(const x of this.tableData){
                  x["type"] == "folder" ? x.icon = "el-icon-folder" : x.icon = "el-icon-document";
                }
                
            })
            .catch(error=>{
                alert("出错啦！！！");
                console.log(error);
            })
        },
        viewDetail(row){
          if(row.type == "folder"){
            return;
          } else {
            return;
          }
        }
    },
    beforeMount() {
        this.localGet();
    }
}
</script>

<style>

</style>