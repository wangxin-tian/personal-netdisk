<template>
    <div class="wrap-menu">
        <el-menu :router="true" 
        background-color="#eee" 
        text-color="#000" 
        active-text-color="#006dcd" 
        default-active="/home"
        :default-openeds="['1', '2']">
            <!-- <el-menu-item index="/one"><i class="el-icon-s-tools"></i>首页</el-menu-item>
          <el-menu-item index="/t"><i class="el-icon-message"></i>全部文件</el-menu-item>
          <el-menu-item index="/r"><i class="el-icon-message"></i>我的分享</el-menu-item>
          <el-menu-item index="/b"><i class="el-icon-user"></i>用户</el-menu-item> -->

            <el-submenu :index="item.path" v-for="item in menulist" :key="item.path">
                <template slot="title">
                    <i :class="item.icon"></i>
                    <span>{{ item.name }}</span>
                </template>
                <el-menu-item :index="it.path" v-for="it in item.sub" :key="it.path">
                    <template slot="title">
                        <i :class="it.icon"></i>
                        <span>{{ it.name }}</span>
                    </template>
                </el-menu-item>
            </el-submenu>
        </el-menu>
    </div>
</template>

<script>
export default {
    name: 'aside_',
    data() {
        return {
            menulist: [],
            level: JSON.parse(sessionStorage.getItem("Userms")).userLevel,
        };
    },
    // created() {
    //     // 表示一打开这个页面，就运行这里
    //     this.level = sessionStorage.getItem("level");
    //     this.getMenuLIst(); // 获取菜单
    // },
    methods: {
        // 获取菜单
        getMenulIst() {
            // 如果登录角色为 role = admin1
            if (this.level == "1") {
                this.menulist = [{
                    name: "首页",
                    path: "1",
                    icon: "el-icon-menu",
                    sub: [{
                        name: "首页咨询",
                        path: "/home",
                        icon: "",
                    }, {
                        name: "全部文件",
                        path: "/all",
                        icon: "",
                    }, {
                        name: "音频",
                        path: "/audio",
                        icon: "",
                    }, {
                        name: "视频",
                        path: "/video",
                        icon: "",
                    }, {
                        name: "图片",
                        path: "/picture",
                        icon: "",
                    }, {
                        name: "可视化",
                        path: "/visual",
                        icon: "",
                    }],
                },
                {
                    name: "其他",
                    path: "2",
                    icon: "el-icon-news",
                    sub: [{
                        name: "分享文件",
                        path: "/share",
                        icon: "el-icon-share",
                    },
                    {
                        name: "回收站",
                        path: "/rubbish",
                        icon: "el-icon-delete",
                    }],
                }, {
                    name: "用户管理",
                    path: "3",
                    icon: "el-icon-news",
                    sub: [{
                        name: "全部用户",
                        path: "/users",
                        icon: "",
                    },
                    {
                        name: "用户状态",
                        path: "/action",
                        icon: "",
                    }],
                }];
            }
            // 如果登录角色为 role = admin2
            else if (this.level == "2") {
                // menuList 用于在上面模板中渲染，建议 ctrl+F 看看怎么用的
                this.menulist = [{
                    name: "首页",
                    path: "1",
                    icon: "el-icon-menu",
                    sub: [{
                        name: "首页咨询",
                        path: "/home",
                        icon: "",
                    }, {
                        name: "全部文件",
                        path: "/all",
                        icon: "",
                    }, {
                        name: "音频",
                        path: "/audio",
                        icon: "",
                    }, {
                        name: "视频",
                        path: "/video",
                        icon: "",
                    }, {
                        name: "图片",
                        path: "/picture",
                        icon: "",
                    }, {
                        name: "可视化",
                        path: "/visual",
                        icon: "",
                    }],
                },
                {
                    name: "其他",
                    path: "2",
                    icon: "el-icon-news",
                    sub: [{
                        name: "分享文件",
                        path: "/share",
                        icon: "el-icon-share",
                    },
                    {
                        name: "回收站",
                        path: "/rubbish",
                        icon: "el-icon-delete",
                    }],
                }];
            }
            // 你还可以继续 else if (role == "****"){}
        },
    },
    beforeMount() {
        // 表示一打开这个页面，就运行这里
        // this.level = sessionStorage.getItem("userLevel");
        this.getMenulIst(); // 获取菜单
    }
}
</script>

<style scoped>

</style>