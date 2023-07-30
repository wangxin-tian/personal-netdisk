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
          width="800"
          prop="name"
          label="文件名称">
          <template slot-scope="scope">
            <div @click="viewDetail(scope.row)" style="cursor: pointer;">
              <i :class="scope.row.icon"></i>
              {{ scope.row.name.length > 20 ? scope.row.name.substring(0 ,10) + "..." : scope.row.name}}
            </div>
          </template>
        </el-table-column>
        <!-- <el-table-column>
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
        </el-table-column> -->
        <el-table-column
          label="日期"
          width="200">
          <template slot-scope="scope">{{ scope.row.folderCreatetime }}</template>
        </el-table-column>
        <el-table-column
          label="文件类型">
          <template slot-scope="scope">{{ scope.row.type }}</template>
        </el-table-column>
      </el-table>

      <!-- <el-dialog :visible.sync="dialogVisible" class="diaaud">
        <audio :src="dialogMusicUrl" controls></audio>
      </el-dialog> -->
    </div>
  </template>
  
  <script>
  export default {
      name: "main_audio",
      data() {
          return {
            tableData: [],
            belongUser: JSON.parse(sessionStorage.getItem("Userms")).userId,
            // dialogVisible: false,
            // dialogMusicUrl: ""
          }
      },
      methods: {
          localGet() {
              this.$axios.post('http://localhost:8088/file/audio', this.belongUser).then(res=>{
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
                    x["type"] == "folder" ? x.icon = "el-icon-folder" : x.icon = "el-icon-headset";
                  }
                  
              })
              .catch(error=>{
                  alert("出错啦！！！");
                  console.log(error);
              })
          },
          viewDetail(row){
            this.$store.commit('setAudioUrl', { url: "", name: "未知"});
            const name = row.name;
            this.$axios.post("http://localhost:8088/file/audition",{fileName: name},
              {responseType:'arraybuffer', headers: {'Accept': 'audio/mpeg'}}).then( res => {
              console.log(res.data);
              let blob = new Blob([res.data],{type: "audio/mpeg"}),
                  reader = new FileReader();
              
              reader.readAsDataURL(blob);
              reader.onload = () => {
                // console.log(reader.result);
                let url = reader.result,
                    musicName = name;
                // this.dialogMusicUrl = url;
                // console.log(name);
                this.$store.commit('setAudioUrl', { url: url, name: musicName});
                console.log("url is setted");
                // console.log(this.$store.state.url);
              }
              reader.onerror = (e) => {
                console.error("FileReader error:", e);
              }
            }).catch(error => {
              console.log(error);
              this.$message({
                type: 'warning',
                message: '系统错误，请联系管理员!'
              });
            })
          }
      },
      beforeMount() {
          this.localGet();
      }
  }
  </script>
  
  <style>

   .diaaud audio {
    width: 100%;
   }
  </style>