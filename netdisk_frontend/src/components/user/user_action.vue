<template>
  <div>
    <el-row :gutter="24">
      <el-col :span="8" v-for="(value, key) in tableData" :key="key">
        <el-card shadow="hover" style="margin-top: 20px;" @click="getFile(value)">
          <div style=" display: flex; flex-direction: row; cursor: pointer;">
            <h2>{{ value.userName }}</h2>
            <div class="spaceUse">
              Space：{{ value.userStatus }} %
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'user_action',
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    getFile(value) {
      console.log(value);
    },
    loadGet() {
      this.$axios.get('http://localhost:8088/status/allstatus').then(res => {
        while(this.tableData.length) {
          this.tableData.pop();
        }
        this.tableData.push(...res.data)
        console.log(res);
      }).catch(Error => {
        console.log(Error);
      })
    }

  },
  beforeMount() {
    this.loadGet();
  }
}
</script>

<style>

.spaceUse {
  margin-left: 30px;
  color: #46464685;
  line-height: 30px;
}
</style>