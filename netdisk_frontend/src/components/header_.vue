<template>
    <header>
        <div style="cursor: pointer;" @click="toallfile">
            <!-- {{插入用户名}} -->
            <span style="margin-top: -10px; color:#333; font-size:30px; "><strong>{{ username }}的个人网盘</strong></span>
        </div>

        <div style="padding-bottom: 1%;" prop="searchmsg">
            <!-- 搜索栏 -->
            <el-input v-model="searchmsg" suffix-icon="el-icon-search" @keyup.enter.native="searchFile"
                placeholder="请输入查询内容"></el-input>
        </div>

        <div class="opt-wrapper">
            <el-dropdown :hide-on-click="false">
                <div class="demo-basic--circle">
                    <div class="block">
                        <el-avatar :size="40" shape="square" :src="avatar" :class="['avatar-info']">
                        </el-avatar>
                    </div>
                </div>
                <el-dropdown-menu slot="dropdown" style="margin-top:-10px;margin-left: -40px;height: auto;">
                    <el-dropdown-item @click.native="toUser"><i class="el-icon-info"></i>个人中心</el-dropdown-item>
                    <el-dropdown-item @click.native="esclogin"><i class="el-icon-switch-button"></i>退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>

    </header>
</template>

<script>
//                <el-dropdown-item><i class="el-icon-info"></i>登入</el-dropdown-item>
//                <el-dropdown-item><i class="el-icon-info"></i>注册</el-dropdown-item>
export default {
    name: "header_",
    data() {
        let imgUrl = () => {
            let url = JSON.parse(sessionStorage.getItem("Userms")).userImgurl;
            console.log("这里这里")
            if ( url !== 'null' ) {
                console.log("url:", url);
                return url;
            }
            return 'https://img1.baidu.com/it/u=4157548555,2051691630&fm=253';
        }
        return {
            username: JSON.parse(sessionStorage.getItem("Userms")).userNickname,
            avatar: imgUrl(),//'https://dhxia.xyz/img/logoo.jpg'
            drawer: false,
            direction: 'ttb',
            searchmsg: ''
        };
    },
    methods: {
        searchFile() {
            // sessionStorage.setItem("search", JSON.stringify({
            //     msg: this.searchmsg
            // }));
            this.$store.commit('setSearchmsg', { searchmsg: this.searchmsg });    
            this.$router.replace("/search").catch(() => {});
        },
        toallfile() {
            this.$router.replace("/all").catch(() => {});
        },
        toUser() {
            // alert("toUser");
            this.$router.replace("/personal");
        },
        esclogin() {
            this.$confirm('确定要离开吗？', '提示', {
                confirmButtonText: '残忍拒绝挽留，继续退出',//确认按钮文字显示
                cancelButtonText: '取消退出',//取消按钮的文字显示
                type: 'warning',
                center: true,//文字居中
                showCancelButton: true,//是否显示取消按钮
                showClose: true,//是否显示左上角的x
                closeOnClickModal: true,//是否可以点击空白处关闭弹窗
            }).then(() => {
                this.$message({
                    type: "success", // 内置了颜色
                    message: "退出登入成功"
                });
                sessionStorage.clear();
                this.$router.push('/');
            }).catch(() => {
                this.$message({
                    type: "info",
                    message: "欢迎回来"
                });
            });
            // sessionStorage.clear();
            // this.$router.replace("/");
        }
    },
    // beforeMount() {
    //     this.username = sessionStorage.getItem("Userms");
    // }
};
</script>

<style scoped>
header {
    display: flex;
    align-items: center;
    justify-content: space-between;

    height: 56px;
    width: 100%;
    border-bottom: #fff 1px;
    box-shadow: 0 3px 6px rgba(53, 53, 53, 0.482), 0 2px 6px rgba(174, 174, 174, 0.04);
}

header div {
    margin: auto;
}

/*设定头像图片样式*/
.avatar-info {
    margin-top: 10px;
    margin-right: 40px;
    cursor: pointer;
}

.el-input {
    width: 150%;
}
</style>
