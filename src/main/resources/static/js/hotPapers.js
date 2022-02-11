$(document).ready(function () {
    makePaperRef();
    function makePaperRef(){
        $.ajax({
            type:'POST',
            url : '/referenceCount',
            asnyc:true,
            contentType: 'application/json',
            processData:false,
            success:function (res) {
                //alert("res:"+ res[0].referenceCount);
                var tableData = res.map(function (item) {
                    return item.referenceCount;
                });
                var nameList = res.map(function (item) {
                    return item.title;
                });

                var option = {
                    label:{
                        show:true,//开启显示
                        position:'top'
                    },
                    tooltip:{
                        trigger:'axis',
                        axisPointer:{type:'shadow'}
                    },
                    grid:{
                        y2:200,
                        left:"12%", //距离边框左部
                        bottom:"20%" //距离边框底部
                    },

                    title : {
                        text: '被引用次数最多的前十篇论文',
                        textStyle:{
                            fontWeight: 'bold',
                            color: '#e4a777'
                        },
                        x:'center'
                    },

                    xAxis:{
                        type:'category',
                        // data:nameList,
                        data:nameList.reverse(),
                        axisLabel : {
                            formatter: function(){
                                return "";
                            }
                        }
                    },
                    yAxis:{
                        type:'value'
                    },
                    series:[{
                        // data:tableData,
                        data:tableData.reverse(),
                        type:'bar',
                        name:'被引用数',
                        barGap:40
                    }]
                };
                // var referCountChart = echarts.init($("#paper-referCount")[0]);
                var referCountChart = echarts.init(document.getElementById("paper-referCount"));
                referCountChart.setOption(option);
            },
            error:function (res) {
                alert(res);
            }
        })
    }
    var axisLabel1 = {
        // 方法2：换行显示
        interval: 0, //强制全部显示，1表示隔一个；2隔两个
        formatter: function (params) {
            var newParamsName = ""; // 最终拼接成的字符串
            var paramsNameNumber = params.length; // 实际标签的个数
            var provideNumber = 6; // 每行能显示的字的个数
            var rowNumber = Math.ceil(paramsNameNumber / provideNumber); // 换行的话，需要显示几行，向上取整
            /**
             * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
             */
            // 条件等同于rowNumber>1
            if (paramsNameNumber > provideNumber) {
                /** 循环每一行,p表示行 */
                for (var p = 0; p < rowNumber; p++) {
                    var tempStr = ""; // 表示每一次截取的字符串
                    var start = p * provideNumber; // 开始截取的位置
                    var end = start + provideNumber; // 结束截取的位置
                    // 此处特殊处理最后一行的索引值
                    if (p == rowNumber - 1) {
                        // 最后一次不换行
                        tempStr = params.substring(start, paramsNameNumber);
                    } else {
                        // 每一次拼接字符串并换行
                        tempStr = params.substring(start, end) + "\n";
                    }
                    newParamsName += tempStr; // 最终拼成的字符串
                }

            } else {
                // 将旧标签的值赋给新标签
                newParamsName = params;
            }
            //将最终的字符串返回
            return newParamsName;
        },
    };
})