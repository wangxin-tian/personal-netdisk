<template>
    <div style="width: 100%; height: 100%; display: flex; flex-direction: row;">
        <div style="width: 40%;height: 80%;
            margin-left: 5%;
            margin-top: 5%;
            background-color: #fff;">
            <div id="content" style="height: 100%"></div>
        </div>
        <div style="width: 40%;height: 80%;
            margin-left: 10%;margin-top: 5%;
            background-color: #fff;">
            <div id="content2" style="height: 100%"></div>
        </div>
    </div>
</template>
  
<script>
import * as echarts from 'echarts';

export default {
    name: 'info_visualization',
    data() {
        return {

        }
    },
    methods: {
        setLeft(nums) {
            console.log(nums);

            let chartDom = document.getElementById('content'),
                myChart = echarts.init(chartDom),
                driveSize = JSON.parse(sessionStorage.getItem("Userms")).driveSize,
                option,sum = 0;

            for (let item of nums) {
                sum += item.fileSize;
            }

            option = {
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top: '5%',
                    left: 'center'
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 40,
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: [
                            { value: sum, name: '已使用的空间' },
                            { value: driveSize - sum, name: '剩余空间' }
                        ]
                    }
                ]
            };

            option && myChart.setOption(option);

            // console.log({
            //     userName: JSON.parse(sessionStorage.getItem("Userms")).userName,
            //     userStatus: Math.floor( 100 * sum / driveSize )
            // });
            this.axios.post('http://localhost:8088/status/update', {
                userName: JSON.parse(sessionStorage.getItem("Userms")).userName,
                userStatus: Math.floor( 100 * sum / driveSize )
            }).then(()=>{});
        },
        myPostL(setFun) {
            this.axios.post('http://localhost:8088/file/rufiles', {
                belongUser: JSON.parse(sessionStorage.getItem("Userms")).userId,
                isDel: 0
            }).then(res => {
                // console.log(res.data);
                setFun(res.data);
            }).catch(console.log);
        },
        myPostR(setFun) {
            this.axios.post('http://localhost:8088/file/filecount', {
                belongUser: JSON.parse(sessionStorage.getItem("Userms")).userId
            }).then(res => {
                // console.log(res.data);
                setFun(res.data);
            }).catch(console.log);
        },
        setRight(nums) {
            let chartDom_right = document.getElementById('content2'),
                myChart_right = echarts.init(chartDom_right),
                option2;

            option2 = {
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    bottom: '10%',
                    left: 'center'
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 40,
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: [
                            { value: nums.picture, name: '图片' },
                            { value: nums.audio, name: '音频' },
                            { value: nums.vidio, name: '视频' },
                            { value: nums.words, name: '文档' },
                            { value: nums.other, name: '其他' }
                        ]
                    }
                ]
            };

            option2 && myChart_right.setOption(option2);
        }
    },
    mounted() {
        this.myPostR(this.setRight);
        this.myPostL(this.setLeft);
    }
}
</script>
  
<style></style>