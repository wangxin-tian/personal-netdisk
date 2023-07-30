<template>
  <div class="audio-wrapper">
    <audio :src="dialogMusicUrl" id="player"></audio>
    <div class="audio-left" @click="setMusic"><i :class="audio_state"></i></div>
    <div class="audio-right">
      <p style="max-width: 536px;">{{ musicName }}</p>
      <div class="progress-bar-bg" id="progressBarBg" @click="audioJump">
        <div class="progress-bar" id="progressBar" :style="{ width: width + 'px' }"></div>
        <div id="progressDot"></div>
      </div>
      <div class="audio-time">
        <span class="audio-length-current" id="audioCurTime">{{ currentTime }}</span>
        <span class="audio-length-total">{{ totalTime }}</span>
      </div>
    </div>
    <div class="audio-download" bufferingThrottled>
      <a :href="dialogMusicUrl" :download="musicName">xz</a>
    </div>
    <div class="audio-exp" @click="audioExp"><i class="el-icon-s-unfold"></i></div>

    <div class="exp-content" id="exp">
      <div v-for="item in musicsbefore" :key="item.name" @click="choseheardmusic(item)">
        {{ item.music }}
      </div>
      <!-- <ul>
        <li v-for="item in musicsbefore" :key="item.music">{{ item.music }}</li>
      </ul> -->
    </div>
  </div>
</template>

<script>
// import { mapState } from 'vuex';  

export default {
  name: 'footer_',
  data() {
    return {
      play: require('@/assets/img/could0.jpg'),
      dialogMusicUrl: '',
      musicName: "",
      audio_state: "el-icon-video-play",
      currentTime: "0:00",
      totalTime: "00:00",
      width: 0,
      myInterval: null,
      musicsbefore: [],
      listen: true
    }
  },
  methods: {
    setMusic() {
      clearInterval(this.myInterval);

      let player = document.getElementById("player"),
        bgwidth = 600,
        totaltime = Math.floor(player.duration),
        time = totaltime,
        minite = Math.floor(time / 60),
        second = Math.floor(time % 60);

      if (this.listen) {
        player.addEventListener('ended', () => {
          this.audio_state = "el-icon-video-play";
        });
        this.listen = false;
      }
      second = second < 10 ? "0" + second : second;
      if (this.dialogMusicUrl !== "") {
        if (this.audio_state === "el-icon-video-play") {
          player.play();
          this.audio_state = "el-icon-video-pause";
          this.musicName = this.$store.state.musicName;
          // console.log(this.$store.state.musicName);
          //console.log(time); //time一开始拿不到，但是之后又可以正常显示，分析：可能是文件的信息不够数据计算的压力大产生了延迟
          this.totalTime = minite + ":" + second;
        } else {
          player.pause();
          this.audio_state = "el-icon-video-play";
        }

        this.myInterval = setInterval(() => {
          // console.log(totaltime,"看这: ");
          if (isNaN(totaltime)) {
            totaltime = Math.floor(player.duration);
            time = totaltime;
            minite = Math.floor(time / 60);
            second = Math.floor(time % 60);
            second = second < 10 ? "0" + second : second;
            this.totalTime = minite + ":" + second;
          }

          time = Math.floor(player.currentTime),
            minite = Math.floor(time / 60),
            second = Math.floor(time % 60),
            second = second < 10 ? "0" + second : second;
          this.currentTime = minite + ":" + second;
          // console.log(this.width + "|" + time + "|" + totaltime)
          this.width = Math.floor(time / totaltime * bgwidth);
          // console.log(this.width)
        }, 1000);
      }
    },
    audioJump(event) {
      console.log("X坐标" + event.offsetX);
      let player = document.getElementById("player");
      const bgwidth = 600,
        totaltime = Math.floor(player.duration),
        setX = event.offsetX,
        jumpTime = setX / bgwidth * totaltime;

      player.currentTime = jumpTime;

      player = null;
    },
    audioExp() {
      let myAudioExp = document.getElementById("exp");

      myAudioExp.style.visibility = "visible";

      setTimeout(() => {
        myAudioExp.style.visibility = "hidden";
      }, 4000);
    },
    choseheardmusic(item) {
      let player = document.getElementById("player");
      player.pause();
      player = "";

      this.$store.commit('setAudioUrl', { url: item.url, name: item.music });
      this.musicName = item.music;
      this.dialogMusicUrl = item.url;

      console.log(this.musicsbefore);
      setTimeout(() => {
        this.setMusic();
      }, 2000);
    }

  },
  computed: {
    // ...mapState(['url']),
  },
  mounted() {
    // setInterval(() => {
    //   if (this.dialogMusicUrl != this.$store.state.url) {
    //     new Promise( () => {
    //       this.dialogMusicUrl = this.$store.state.url;
    //     }).then( () => {
    //       this.setMusic();
    //     }).catch( console.log );
    //   }
    // }, 1000);
    this.$store.watch(
      state => state.url,
      (newVal) => {
        {
          this.audio_state = "el-icon-video-play";
          this.musicName = "";
          let player = document.getElementById("player");
          player.pause();
          player = null;
        }

        if (this.dialogMusicUrl != newVal) {
          this.dialogMusicUrl = newVal;

          if (this.musicsbefore.length == 5) {
            this.musicsbefore.shift();
          }
          const name = this.$store.state.musicName;
          if (name != "未知") {
            this.musicsbefore.push({
              music: name.length > 20 ? name.substring(0, 10) + "..." : name,
              url: newVal
            });

            setTimeout(() => {
              console.log("set the url of music");
              this.setMusic();
            }, 2000);
          }
        }
      }
    )
  }
}
</script>

<style>
/* .footer_audio {
  width: 1000px;
  margin-top: 2px;
  background-color: #b3c0d100;
  height: 100%;
 } */

.audio-wrapper {
  display: flex;
  flex-direction: row;
  background-color: #b3c0d100;
  margin: auto;
  max-width: 800px;
  height: 60px;
}

.audio-left {
  text-align: center;
  width: 10%;
  margin-right: 15px;
}

.audio-left i {
  line-height: 60px;
  font-size: 40px;
  position: relative;
  display: initial;
  /* 解除与app的样式冲突 */
  cursor: pointer;
}

.audio-right {
  margin-right: 0;
  width: 76%;
  height: 100%;
}

.audio-right p {
  font-size: 15px;
  line-height: 15px;
  height: 20%;
  margin: 8px 0;
  color: #000;
  /* 歌曲名称只显示在一行，超出部分显示为省略号 */
  overflow: visible;
  white-space: nowrap;
  text-overflow: ellipsis;
  max-width: 243px;
  /* 要适配小屏幕手机，所以最大宽度先设小一点，后面js根据屏幕大小重新设置 */
}

.progress-bar-bg {
  display: flex;
  flex-direction: row;
  background-color: #d9d9d9;
  position: relative;
  height: 2%;
  cursor: pointer;
  width: 600px;
}

.progress-bar {
  background-color: #649fec;
  height: 100%;
}

#progressDot {
  content: " ";
  width: 10px;
  height: 10px;
  border-radius: 50%;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  background-color: #3e87e8;
  position: relative;
  left: 0;
  top: 50%;
  margin-top: -5px;
  margin-left: -5px;
  cursor: pointer;
}

.audio-time {
  line-height: 32px;
  overflow: hidden;
  margin-top: -1px;
}

.audio-length-total {
  float: right;
  font-size: 12px;
}

.audio-length-current {
  float: left;
  font-size: 12px;
}

.audio-download {
  width: 10%;
  color: #000;
}

.audio-exp {
  line-height: 60px;
  font-size: 30px;
  position: relative;
  display: initial;
  /* 解除与app的样式冲突 */
  cursor: pointer;
}

.exp-content {
  position: fixed;
  bottom: 65px;
  left: 1200px;
  background-color: #aeaeae82;
  border-radius: 15px;
  width: 15%;
  height: auto;
  visibility: hidden;
}

.exp-content div {
  list-style-type: none;
  line-height: 24px;
  font-size: 14px;
  cursor: pointer;
}
</style>