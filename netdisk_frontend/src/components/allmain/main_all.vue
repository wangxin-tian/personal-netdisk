<template>
  <div>
    <div style="margin-bottom: 5px; display: flex; ">
      <div class="bts-manage">
        <div class="upload-btn">
          <el-upload class="upload-demo" action="http://localhost:8088/file/upload" :show-file-list="false"
            :on-success="handleSuccess" :before-upload="beforeAvatarUpload">
            <el-button size="mini" type="primary" style="height: 32px; border-radius: 30px;">上传</el-button>
          </el-upload>
        </div>
        <div class="bts-add-folder" @click="dialogFolderVisible = true">
          <div size="mini"><strong>新建文件夹</strong></div>
        </div>
        <div class="bts-add-file" @click="dialogFileVisible = true">
          <div size="mini"><strong>新建文本文件</strong></div>
        </div>
        <!-- <div class="bts-on-load">
              <el-button
                size="mini"
                @click="handleDelete(scope.$index, scope.row)">在线下载</el-button>
            </div> -->
      </div>
      <div class="crumb">
        <el-breadcrumb separator="" v-for="(value, key) in breadcrumb" :key="key">
          <el-breadcrumb-item>{{ value + ` > ` }}</el-breadcrumb-item>
          <!-- <i class="el-icon-arrow-right"></i> -->
        </el-breadcrumb>
      </div>
    </div>

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
      <el-table-column prop="operate" label="操作" align="right" width="250">
        <!-- <template slot="header">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
        </template> -->
        <template slot-scope="scope">
          <!-- <el-button size="small" @click="updateUser(scope.row)">修改</el-button> -->
          <el-button v-if="scope.row.type === 'file'" icon="el-icon-download" @click="downloadFile(scope.row)"
            circle></el-button>
          <el-button v-if="scope.row.type === 'file'" type="primary" icon="el-icon-share" @click="toshare(scope.row)"
            circle></el-button>
          <el-popconfirm title="确定删除吗？" style="margin-left: 5px" @confirm="updatemg(scope.row)">
            <!-- <el-button slot="reference" size="small" type="danger">删除</el-button> -->
            <el-button slot="reference" type="danger" icon="el-icon-delete" circle></el-button>
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
    <el-dialog @close="closePicture" :visible.sync="dialogVisible" :lock-scroll="false" top="5vh" width="70%"
      class="diapic">
      <img :src="dialogImageUrl" alt />
    </el-dialog>

    <el-dialog :visible.sync="dialogVideoVisible" :lock-scroll="false" top="2vh" width="70%" @close="closeVideo"
      class="diaaud">
      <video :src="dialogVideoUrl" width="100%" controls></video>
    </el-dialog>

    <el-dialog title="请输入新文件夹名称" :visible.sync="dialogFolderVisible" top="30vh" width="24%" :lock-scroll="false">
      <el-input v-model="NewfolderName" autocomplete="off" @keyup.native.enter="createNewFolder"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogFolderVisible = false">取 消</el-button>
        <el-button type="primary" @click="createNewFolder">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="请输入新建文件名称" :visible.sync="dialogFileVisible" top="30vh" width="24%" :lock-scroll="false">
      <el-input v-model="NewfileName" autocomplete="off" @keyup.native.enter="createNewFile"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogFileVisible = false">取 消</el-button>
        <el-button type="primary" @click="createNewFile">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="请输入分享信息" :visible.sync="dialogShareVisible" top="30vh" width="24%" :lock-scroll="false">
      <el-input v-model="shareMsg" autocomplete="off" @keyup.native.enter="shareFile"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogShareVisible = false">取 消</el-button>
        <el-button type="primary" @click="shareFile">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
  
<script>
export default {
  name: "main_all",
  data() {
    return {
      tableData: [],
      belongUser: JSON.parse(sessionStorage.getItem("Userms")).userId,
      search: "",
      breadcrumb: [],
      shareRow: null,
      dialogVisible: false,
      shareMsg: '',
      dialogVideoVisible: false,
      dialogFolderVisible: false,
      dialogFileVisible: false,
      dialogShareVisible: false,
      dialogImageUrl: "",
      dialogVideoUrl: "",
      NewfileName: "",
      NewfolderName: ""
    }
  },
  methods: {
    getFolders(father, user, state) {
      this.$axios.post('http://localhost:8088/folder/allfolders', {
        folderFather: father, belongUser: user, isdel: state
      }).then(res => {
        console.log(res.data);
        for (const x of res.data) {
          // let time = new Date();
          if (!x["type"]) {
            x["type"] = 'folder';
            x["name"] = x.folderName;
          }
          x["folderCreatetime"] = new Date(x["folderCreatetime"]).toLocaleDateString();
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
    getFiles(user, location, state) {
      this.$axios.post('http://localhost:8088/file/userfile', {
        belongUser: user, fileLocation: location, isDel: state
      }).then(res => {
        console.log(res.data);
        for (const x of res.data) {
          // let time = new Date();
          if (!x["type"]) {
            x["type"] = 'file';
            x["name"] = x.fileName;
          }
          x["folderCreatetime"] = new Date(x["uploadTime"]).toLocaleDateString();
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
    insertFile(url, filemsg) {
      this.$axios.post(url, filemsg).then(res => {
        console.log(res.data);
      });
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
        this.localGet();
      }, 1000)
    },
    updatemg(row) {
      if (row.type === "folder") {
        this.myPost('http://localhost:8088/folder/updatefolder', { folderId: row.folderId, isdel: 1 });
      } else {
        this.myPost('http://localhost:8088/file/updatefile', { userfileId: row.userfileId, isDel: 1 });
      }
      this.delayLoad();
    },
    localGet() {
      //加载前设置一下目录
      sessionStorage.setItem("dirms", JSON.stringify([0]));
      this.breadcrumb = ['全部文件'];
      this.belongUser = this.belongUser.toString();
      // console.log(this.belongUser);
      this.getFolders(0, this.belongUser, 0);
      this.getFiles(this.belongUser, 0, 0);
    },
    handleSuccess(res, file) {
      let arr = JSON.parse(sessionStorage.getItem("dirms"));

      if (arr.length === 1) {
        this.delayLoad();
      } else {
        while (this.tableData.length) {
          this.tableData.pop();
        }
        let id = arr[arr.length - 1];
        this.getFolders(id, this.belongUser, 0);
        this.getFiles(this.belongUser, id, 0);
      }

      console.log(res, file);
    },
    beforeAvatarUpload(file) {
      // const isJPG = (file.type === 'image/jpeg') || (file.type === 'video/mp4') || (file.type === 'image/png');
      const isLt2M = file.size / 1024 / 1024 < 20,
        name = file.name,
        size = file.size,
        dir = JSON.parse(sessionStorage.getItem("dirms")),
        url = Number(dir[dir.length - 1]),
        time = new Date(),
        user = JSON.parse(sessionStorage.getItem("Userms")).userId,
        type = file.type.includes('image') ?
          1 : file.type === 'audio/mpeg' ?
            2 : file.type === 'video/mp4' ?
              3 : file.type.includes('text') || file.name.includes(".md") ?
                4 : 5;

      if (!isLt2M) {
        this.$message.error('文件太大');
      }

      this.axios.post('http://localhost:8088/file/rufiles', {
        belongUser: JSON.parse(sessionStorage.getItem("Userms")).userId,
        isDel: 0
      }).then(res => {
        console.log(res.data);
        let driveSize = JSON.parse(sessionStorage.getItem("Userms")).driveSize,
          sum = 0;
        for (let item of res.data) {
          sum += item.fileSize;
        }

        if (driveSize - sum < 0) {
          return false;
        } else {
          //systemfile中添加
          this.insertFile('http://localhost:8088/system/upsfile', {
            fileName: name,
            fileUrl: url,
            belongUser: user,
            fileType: type,
            fileSize: size,
            uploadTime: time
          });
          //在用户文件对应的表中添加
          this.insertFile('http://localhost:8088/file/upufile', {
            fileName: name,
            belongUser: user,
            fileType: type,
            fileSize: size,
            fileLocation: url,
            uploadTime: time
          });
        }
      }).catch(console.log);

      return isLt2M;
    },
    getSource(type, name) {
      if (type === '1') {
        // this.$message.success('1');
        this.$axios.post("http://localhost:8088/file/preview", { fileName: name }, { responseType: 'arraybuffer', headers: { 'Accept': 'image/jpeg' } }).then(res => {
          console.log(res);
          let blob = new Blob([res.data], { type: "image/jpeg" }),
            reader = new FileReader();

          reader.readAsDataURL(blob);
          reader.onload = () => {
            // console.log(reader.result);
            this.dialogImageUrl = reader.result;
            this.dialogVisible = true;
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
      } else if (type === '2') {
        // this.$message.success('2');
        this.$axios.post("http://localhost:8088/file/audition", { fileName: name },
          { responseType: 'arraybuffer', headers: { 'Accept': 'audio/mpeg' } }).then(res => {
            console.log(res.data);
            let blob = new Blob([res.data], { type: "audio/mpeg" }),
              reader = new FileReader();

            reader.readAsDataURL(blob);
            reader.onload = () => {
              // console.log(reader.result);
              let url = reader.result,
                musicName = name;
              // this.dialogMusicUrl = url;
              // console.log(name);
              this.$store.commit('setAudioUrl', { url: url, name: musicName });
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
      } else if (type === '3') {
        // this.$message.success('3');
        this.$store.commit('setAudioUrl', { url: "", name: "未知" });
        setTimeout(() => {
          this.$axios.post("http://localhost:8088/file/audition", { fileName: name },
            { responseType: 'arraybuffer', headers: { 'Accept': 'video/mp4' } }).then(res => {
              console.log(res);
              let blob = new Blob([res.data], { type: "video/mp4" }),
                reader = new FileReader();

              reader.readAsDataURL(blob);
              reader.onload = () => {
                // console.log(reader.result);
                this.dialogVideoUrl = reader.result;
                this.dialogVideoVisible = true;
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
        }, 500);
      } else if (type === '4') {
        this.$message.success('4');
        this.$axios.post("http://localhost:8088/file/audition", { fileName: name },
          { responseType: 'arraybuffer', headers: { 'Accept': 'text/plain' } }
        ).then(res => {
          let blob = new Blob([res.data], { type: "text/plain" }),
            url = URL.createObjectURL(blob);
          console.log({ name });
          this.$router.push({ path: '/mytext', query: { url, name } });
        }).catch(console.log)
      } else {
        this.$message.error('5');
      }
    },
    viewDetail(row) {
      console.log(row);
      if (row.type === "folder") {
        this.$message.success('folder');
        while (this.tableData.length) {
          this.tableData.pop(); // 首先清理表单数据
        }
        this.getFolders(row.folderId, this.belongUser, 0);
        this.getFiles(this.belongUser, row.folderId, 0);

        let arr = JSON.parse(sessionStorage.getItem("dirms"));
        this.breadcrumb.push(row.folderName);
        arr.push(row.folderId);
        sessionStorage.setItem("dirms", JSON.stringify(arr));
      } else if (row.type === "file") {
        // this.$message.success('file');
        // this.$axios.get("", {}).then().catch()
        this.getSource(row.fileType, row.fileName);
      } else {
        this.$message.error('出错了，文件无法打开');
      }
    },
    downloadFile(row) {
      console.log(row);
      if (row.fileType === "1") {
        this.getFileUrl(row.fileName, "image/jpeg");
      } else if (row.fileType === "2") {
        this.getFileUrl(row.fileName, "audio/mpeg");
      } else if (row.fileType === "3") {
        this.getFileUrl(row.fileName, "video/mp4");
      } else if (row.fileType === "4") {
        this.getFileUrl(row.fileName, "text/plain");
      } else if (row.fileType === "5") {
        this.getFileUrl(row.fileName, "application/octet-stream");
      } else {
        this.$message.error('出错了，文件无法下载');
      }
    },
    shareFile() {
      console.log(this.shareRow);
      this.dialogShareVisible = false
      let name = JSON.parse(sessionStorage.getItem("Userms")).userNickname,
        file = {
          shareUser: this.belongUser,
          shareUserfileId: this.shareRow.userfileId,
          shareComment: this.shareRow.fileType + "&" + name + "&" + this.shareMsg + "&" + this.shareRow.fileName,
          fileLocation: this.shareRow.fileLocation,
          shareDate: new Date()
        }

      this.axios.post('http://localhost:8088/share/sharefile', file).then(res => {
        console.log(res);
        this.shareMsg = '';
      }).catch();
    },
    closeVideo() {
      this.dialogVideoUrl = "";
    },
    closePicture() {
      this.dialogImageUrl = '';
    },
    getFileUrl(name, mimeType) {
      this.$axios.post("http://localhost:8088/file/preview",
        { fileName: name },
        { responseType: 'arraybuffer', headers: { 'Accept': mimeType } }
      ).then(res => {
        console.log(res);
        let blob = new Blob([res.data], { type: mimeType }),// 将响应转换为Blob对象
          url = URL.createObjectURL(blob), // 创建Blob URL
          a = document.createElement('a');
        a.href = url;
        a.download = name; // 指定文件名
        a.click(); // 模拟点击链接下载文件
        URL.revokeObjectURL(url); // 释放Blob URL
        this.$message.success('下载成功啊');
      }).catch(error => {
        console.log(error);
        this.$message({
          type: 'warning',
          message: '系统错误，请联系管理员!'
        });
      })
    },
    createNewFolder() {
      this.dialogFolderVisible = false;
      let arr = JSON.parse(sessionStorage.getItem("dirms")),
        fatherId = arr[arr.length - 1],
        folder = {
          folderName: this.NewfolderName,
          folderFather: fatherId,
          belongUser: this.belongUser,
          folderCreatetime: new Date(),
          isdel: 0
        }

      this.axios.post('http://localhost:8088/folder/newfolder', folder).then(res => {
        console.log(res);
        while (this.tableData.length) {
          this.tableData.pop(); // 首先清理表单数据
        }
        this.getFolders(fatherId, this.belongUser, 0);
        this.getFiles(this.belongUser, fatherId, 0);
      }).catch(console.log);
    },
    createNewFile() {
      this.dialogFileVisible = false;
      let arr = JSON.parse(sessionStorage.getItem("dirms")),
        location = arr[arr.length - 1],
        file = {
          fileName: this.NewfileName + ".txt",
          belongUser: this.belongUser,
          fileType: "4",
          fileSize: 0,
          fileLocation: location,
          uploadTime: new Date(),
          isDel: 0
        }

      this.axios.post('http://localhost:8088/file/upufile', file).then(res => {
        console.log(res);
        let blob = new Blob([""], { type: 'text/plain' }),
          myfile = new File([blob], file.fileName, { type: "text/plain" }), url;

        blob = new Blob([myfile], { type: myfile.type });
        url = URL.createObjectURL(blob);
        this.$router.push({ path: '/mytext', query: { url: url, name: file.fileName } });
      }).catch(console.log);
    },
    toshare(row) {
      this.dialogShareVisible = true;
      this.shareRow = row;
    }
  },
  beforeMount() {
    this.localGet();
  }
}
</script>
  
<style scoped>
.bts-manage {
  color: rgb(255, 255, 255);
  width: 24%;
  display: flex;
  flex-direction: row;
  padding: auto;
  margin-left: 1%;
  margin-top: -10px;
  text-align: center;
  font-size: 13px;
  cursor: pointer;
}

.crumb {
  padding: 0px;
  margin-left: 1%;
  display: flex;
  flex-direction: row;
}

.upload-btn {
  line-height: 30px;
  width: 20%;
  border: 1px #E9EEF3 solid;
  border-radius: 16px;
}

.bts-add-folder {
  line-height: 30px;
  color: #168cf4;
  background-color: #fff;
  width: 40%;
  border: 1px #E9EEF3 solid;
  border-top-left-radius: 16px;
  border-bottom-left-radius: 16px;
}

/* .bts-add-file {
      padding: 2%;
      width: 32%;
      border: 1px #000 solid;
    } */

.bts-add-file {
  line-height: 30px;
  background-color: #75bfff;
  width: 40%;
  border: 1px #E9EEF3 solid;
  border-top-right-radius: 16px;
  border-bottom-right-radius: 16px;
}

.diapic img {
  height: 450px;
  /* width: 100%; */
}
</style>