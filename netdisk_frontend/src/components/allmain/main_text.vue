<template>
    <div style="width: 100%;">
        <div class="title" style="cursor: pointer;">
            <div style="line-height: 40px;"><strong>{{ textName }}</strong></div>
        </div>
        <div class="saveText">
            <el-button slot="reference" :icon="saveIcon" @click="saveNew" circle></el-button>
        </div>
        <div class="content">
            <div id="mydiv"></div>
        </div>
    </div>
</template>

<script>
import * as marked from 'marked';

export default {
    name: 'main_text',
    data() {
        return {
            textName: '',
            saveIcon: "el-icon-check",
            userName: JSON.parse(sessionStorage.getItem("Userms")).userName,
        }
    },
    methods: {
        saveNew() {
            this.saveIcon = "el-icon-loading";
            let mydiv = document.getElementById("mydiv"),
                blob = new Blob([ mydiv.innerText ], { type: 'text/plain' }),
                myfile = new File([blob], this.textName, { type: "text/plain" }),
                formData = new FormData();

            formData.append('file', myfile);
            
            this.axios.post('http://localhost:8088/file/upload', formData).then(res => {
                console.log(res);
                setTimeout(()=>{
                    this.saveIcon = "el-icon-check";
                }, 1000);
            }).catch(console.log);
        }
    },
    mounted() {
        // 获取URL参数中的Blob URL
        let blobUrl = this.$route.query.url,
            name = this.$route.query.name,
            mydiv = document.getElementById("mydiv"),
            reader = new FileReader(),
            html, text;

        this.textName = name;
        // 将Blob URL转换为Blob数据
        fetch(blobUrl)
            .then(response => response.blob())
            .then(blobData => {
                // 处理Blob数据
                console.log("看这里： ", blobData);
                reader.readAsText(blobData);
                reader.onload = () => {
                    text = reader.result;
                    if (name.includes(".md")) {
                        mydiv.innerHTML = marked.parse(text);
                    } else {
                        html = text.replace(/(?:\n)/g, '<br>');
                        mydiv.innerHTML = html;
                        mydiv.contentEditable = 'true';
                    }
                }
            });
    }
}
</script>

<style>
.title {
    margin-top: -10px;
    height: 40px;
    background-color: #fff;
    border: 2px #dedede solid;
    border-radius: 5px;
    box-shadow: 0 3px 6px rgba(166, 166, 166, 0.12), 0 2px 6px rgba(174, 174, 174, 0.04);
}

.content {
    text-align: left;
    margin-top: 10px;
    height: 500px;
    font-size: 12px;
    line-height: 16px;
    background-color: #fff;
    border: 2px #dedede solid;
    border-radius: 5px;
    box-shadow: 0 3px 6px rgba(166, 166, 166, 0.12), 0 2px 6px rgba(174, 174, 174, 0.04);
}

.content blockquote {
    line-height: 20px;
}

.content p>strong {
    line-height: 20px;
}

.content h3 {
    line-height: 30px;
}

#mydiv {
    height: 460px;
    /* 设置div高度 */
    overflow: auto;
    /* 自动添加滚动条 */
    
    padding: 20px;
}

pre {
    width: 1180px;
    color: #333;
    overflow: auto;
    border-radius: 10px;
    background-color: rgba(191, 191, 191, 0.489);
    padding: 10px;
}

.content img {
    width: 400px;
}

.saveText {
    position: fixed;
    top: 136px;
    right: 30px;
}
</style>