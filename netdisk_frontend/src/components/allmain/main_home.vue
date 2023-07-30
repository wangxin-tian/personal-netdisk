<template>
  <div class="range">
    <div>
        <el-carousel :interval="4000" type="card" height="300px">
            <el-carousel-item v-for="item in src" :key="item">
              <!-- <h3 class="medium">{{ item }}</h3> -->
              <el-image :src="item"></el-image>
            </el-carousel-item>
        </el-carousel>
    </div>
    <div>
        <div class="message"> 
          <div style="width: 48%; margin-left: 2%; height: 100%;border-right: 1px #dedede solid;"> 
            <div style="width: 98%;">
            <el-collapse v-model="activeNames" @change="handleChange" accordion>
              <el-collapse-item name="1">
                <template slot="title">
                  一致性 Consistency<i class="header-icon el-icon-info"></i>
                </template>
                <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
                <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
              </el-collapse-item>
              <el-collapse-item title="反馈 Feedback" name="2">
                <div>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；</div>
                <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div>
              </el-collapse-item>
              <el-collapse-item title="效率 Efficiency" name="3">
                <div>简化流程：设计简洁直观的操作流程；</div>
                <div>清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；</div>
                <div>帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。</div>
              </el-collapse-item>
              <el-collapse-item title="可控 Controllability" name="4">
                <div>用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；</div>
                <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div>
              </el-collapse-item>
            </el-collapse>
            </div>
          </div> 
          <div style="width: 48%; margin-left: 1%; height: 100%;"> 
            <div style="width: 98%; font-size: large;">
            <span style="font-size: 24px;"><strong>个人总结：</strong></span><br>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              SSM个人网盘系统是一个简洁高效的在线文件存储和管理系统，它使用了强大灵活的SSM框架和安全可靠的MySQL数据库，提供了多种文件操作和分享功能，是一个实用的毕业设计项目。你们有没有遇到过这样的问题：你想要保存一些重要的文件，但是你的电脑硬盘空间不够，或者你想要在不同的设备上访问你的文件，但是你没有办法同步或传输？如果你有这样的问题，那么SSM个人网盘系统就是为你量身定制的解决方案。它可以让你随时随地上传和下载你的文件，无论是图片、文档、音乐还是视频。它还可以让你方便地管理你的文件夹，创建、删除、重命名、移动等。更重要的是，它还可以让你分享你的文件给其他人，只要生成一个URL链接，就可以让别人下载或查看你的文件。例如，你可以轻松地将你的毕业论文分享给你的老师或同学，让他们给你提供意见或建议。相信你们也会喜欢这个系统，它不仅可以节省你的硬盘空间，还可以提高你的工作效率和协作能力。</span>
            </div>
          </div> 
        </div> 
    </div>
  </div>
</template>

<script>

export default {
    name: 'main_home',
    data() {
      return {
        activeNames: ['1'],
        pg1: require('@/assets/img/could0.jpg'),
        pg2: require('@/assets/img/could1.jpg'),
        pg3: require('@/assets/img/could2.jpg'),
        src: []
      };
    },
    methods: {
      handleChange(val) {
        console.log(val);
      },
      handleBackgroud(){
        this.src.push(this.pg1,this.pg2,this.pg3);
      }
    },
    beforeMount() {
        this.handleBackgroud();
    }
}
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .message{
    text-align: left;
    display: flex;
    padding-top: 10px;
    padding: 15px;
    width: 98%;
    height: 310px;
    padding: 15px; 
    background-color: #fff;
    border: 2px #dedede solid;
    border-radius: 5px;
    box-shadow: 0 3px 6px rgba(166, 166, 166, 0.12), 0 2px 6px rgba(174, 174, 174, 0.04);
  }

  .msflexright {
    margin-left: 2%;
  }

</style>