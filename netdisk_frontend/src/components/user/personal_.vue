<template>
    <div class="rang">
        <div class="personborder">
            <div style="float: left;width: 20%; height: 100px">
                <el-image style="float: left;width: 100px; height: 100px; " :src="form.userImgurl"></el-image>
            </div>
            <div style="float: right; width: 80%; height: 100px; cursor: pointer;" @click="toEdit">
                <!-- <el-button @click="toindex" class="escperson">回退</el-button> -->
                <h1 class="title">个人中心</h1>
                <!-- <div class="title">个人中心</div> -->
            </div>
            <el-descriptions class="margin-top" title="" :column="1" direction="horizontal" border>
                <el-descriptions-item label="网盘名" label-class-name="my-label" content-class-name="my-content">{{ form.userNickname }}</el-descriptions-item>
                <el-descriptions-item label="用户名">{{ form.userName }}</el-descriptions-item>
                <el-descriptions-item label="邮箱">{{ form.userEmail }}</el-descriptions-item>
                <el-descriptions-item label="磁盘空间">{{ form.driveSize }}KMB</el-descriptions-item>
                <el-descriptions-item label="居住地" :span="2">地球村</el-descriptions-item>
                <el-descriptions-item label="备注">
                    <el-tag size="small">学生</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="联系地址">太平洋西的一个神秘的东方国度</el-descriptions-item>
            </el-descriptions>
        </div>

        <el-dialog title="用户详情" :visible.sync="dialogFormVisible" :lock-scroll="false" width="30%">
            <el-form :model="form" style="text-align: left;">
                <el-form-item label="用户名称:" :label-width="formLabelWidth">
                    {{ form.userName }}
                </el-form-item>
                <el-form-item label="用户邮箱:" :label-width="formLabelWidth">
                    {{ form.userEmail }}
                </el-form-item>
                <el-form-item label="空间大小:" :label-width="formLabelWidth">
                    {{ form.driveSize }}
                    <!-- <el-input v-model="form.name" autocomplete="off" class="inputbox"></el-input> -->
                </el-form-item>
                <el-form-item label="用户权限:" :label-width="formLabelWidth">
                    {{ form.userLevel === "1" ? "管理员权限" : "普通用户权限" }}
                </el-form-item>
                <el-form-item label="网盘名称:" :label-width="formLabelWidth">
                    <!-- {{ form.userEmail }} -->
                    <el-input v-model="form.userNickname" autocomplete="off" class="inputbox"></el-input>
                </el-form-item>
                <el-form-item label="头像地址:" :label-width="formLabelWidth">
                    <!-- {{ form.userImgurl.length > 40 ? form.userImgurl.substring(0, 40) + "..." : form.userImgurl }} -->
                    <el-input v-model="form.userImgurl" autocomplete="off" class="inputbox" ></el-input>
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
    name: 'personal_',
    data() {
        return {
            dialogFormVisible: false,
            formLabelWidth: '120px',
            form: {
                userId: '',
                userName: '',
                userEmail: '',
                driveSize: '',
                userLevel: '',
                userNickname: '',
                userImgurl: ''
            },
        }
    },
    methods: {
        toEdit() {
            this.dialogFormVisible = true;
            // alert("你要离开吗╯︿╰");
            // this.$router.replace("/index");
        },
        commitMsg() {
            this.dialogFormVisible = false;
            this.axios.post('http://localhost:8088/user/upself', {
                userId: this.form.userId, 
                userNickname: this.form.userNickname, 
                userImgurl: this.form.userImgurl
            }).then( res => {
                console.log(res);
                let usermsg = JSON.parse(sessionStorage.getItem("Userms"));

                usermsg.userNickname = this.form.userNickname;
                usermsg.userImgurl = this.form.userImgurl;
                sessionStorage.setItem('Userms', JSON.stringify(usermsg))
            })
        }
    },
    beforeMount() {
        const usermsg = JSON.parse(sessionStorage.getItem("Userms"));
        this.form = {
            userId: usermsg.userId,
            userName: usermsg.userName,
            userPassword: '',
            userEmail: usermsg.userEmail,
            driveSize: usermsg.driveSize,
            userLevel: usermsg.userLevel,
            userNickname: usermsg.userNickname,
            userImgurl: usermsg.userImgurl
        }
    }
}
</script>

<style scoped>
.rang {
    width: 40%;
    height: 50%;
    margin: auto;
}

.title {
    border: 0px;
    box-shadow: 0px 0px 0px;
    font-size: 25px;
    float: left;
    width: 100%;
    line-height: 100px;
    color: #c3c3c3;
    text-align: center;
}

.personborder {
    background-color: #fff;
    border: 2px #c3c3c3 solid;
    border-radius: 15px;
    box-shadow: 0 3px 6px rgba(0, 0, 0, .12), 0 2px 6px rgba(0, 0, 0, .04);
    margin-top: 6%;
    padding: 15px;
}

.my-label {
    background: #E1F3D8;
}

.my-content {
    background: #FDE2E2;
}
</style>