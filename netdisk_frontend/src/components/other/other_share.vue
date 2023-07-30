<template>
    <div>
        <el-row :gutter="24">
            <el-col :span="12" v-for="(value, key) in tableData" :key="key">
                <el-card shadow="hover" style="margin-top: 20px;" >
                    <div style=" display: flex; flex-direction: row; cursor: pointer;" @click="getFile(value)">
                        <img v-if="Boolean(value.src)" :src="value.src" class="image" width="100" height="100">
                        <h2>{{ value.type === "1" ? "图片" : value.type === "2" ? "音乐" : value.type === "3" ? "视频" :
                            value.type === "4" ? "文本" : "其他" }}</h2>
                        <div class="sendfileName">{{ value.fileName.length > 20 ? value.fileName.substring(0, 12) + "..." :
                            value.fileName }}</div>
                        <div class="wraptimeandcontent">
                            <div class="sendTime">
                                发布时间：{{ value.date }}
                            </div>
                            <div class="sendContent">
                                内容介绍：{{ value.msg }}
                            </div>
                        </div>
                        <div class="sendUser">
                            发布人：{{ value.name }}
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <el-dialog @close="closePicture" :visible.sync="dialogVisible" :lock-scroll="false" top="5vh" width="70%"
            class="diapic">
            <img :src="dialogImageUrl" alt />
        </el-dialog>

        <el-dialog :visible.sync="dialogVideoVisible" :lock-scroll="false" top="2vh" width="70%" @close="closeVideo"
            class="diaaud">
            <video :src="dialogVideoUrl" width="100%" controls></video>
        </el-dialog>

    </div>
</template>
  
<script>
export default {
    name: 'other_share',
    data() {
        return {
            tableData: [],
            dialogVisible: false,
            dialogVideoVisible: false,
            dialogImageUrl: "",
            dialogVideoUrl: "",
        }
    },
    methods: {
        getFile(value) {
            const type = value.type,
                name = value.fileName;

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
            console.log(value);
        },
        loadGet() {
            this.axios.get('http://localhost:8088/share/findall').then((res) => {

                for (let item of res.data) {
                    item.date = new Date(item.shareDate).toLocaleDateString();
                    const arr = item.shareComment.split("&");
                    item.type = arr[0];
                    item.name = arr[1];
                    item.msg = arr[2];
                    item.fileName = arr[3];
                }
                this.tableData.push(...res.data);
                console.log(this.tableData);
            });
        },
        closeVideo() {
            this.dialogVideoUrl = "";
        },
        closePicture() {
            this.dialogImageUrl = '';
        }
    },
    beforeMount() {
        this.loadGet();
    }
}
</script>
  
<style>
.wraptimeandcontent {
    margin-left: 10px;
    width: 200px;
    text-align: left;
}

.sendfileName {
    text-align: left;
    margin-left: 10px;
    width: 100px;
}

.sendTime {
    color: #46464685;
}

.sendContent {
    color: #000000;
}

.sendUser {
    font-size: 24px;
}
</style>