$(document).ready(function () {

    interaction();
    function interaction(){
        var dom = document.getElementById("container2");
        var myChart = echarts.init(dom);
        myChart.showLoading();
        var app = {};
        option = null;
        $.ajax({
            type: 'GET',
            url: '/links',
            async: true,
            contentType: 'application/json',
            processData: false,
            dataType:"json",
            success: function (res) {
                alert("成功")
                nodes=res.nodesList;
                links=res.linksList;
                categoryArray=[{name:"作者"},{name:"机构"},{name:"研究方向"}];
                jsondata={"categories":categoryArray,"nodes":nodes,"links":links};
                var graph = jsondata;
                graph.nodes.forEach(function (node) {
                    node.itemStyle = null;
                    node.symbolSize /= 10;
                    node.label={
                        show:node.value > 50
                    }
                });
                // option = {
                //     title: {
                //         text: 'Knowledge Graph',
                //         subtext: 'Author & Affiliation & Research Fields',
                //         top: 'bottom',
                //         left: 'center'
                //     },
                //     tooltip: { //工具箱
                //         show:true,
                //         feature:{
                //             restore:{//还原
                //                 show:true
                //             }
                //         }
                //     },
                //     legend: [{
                //         data: graph.categories.map(function (a) {
                //             return a.name;
                //         })
                //     }],
                //     animationDuration: 1500,
                //     animationEasingUpdate: 'quinticInOut',
                //     animation: false,
                //
                //     series : [
                //         {
                //             name: 'Knowledge Graph',
                //             type: 'graph',
                //             layout: 'force',
                //             data: graph.nodes,
                //             links: graph.links,
                //             categories: graph.categories,
                //             roam: true,
                //             focusNodeAdjacency:true,
                //             itemStyle:{
                //                 borderColor:'#fff',
                //                 borderWidth:1,
                //                 shadowBlur:10,
                //                 shadowColor:'rgba(0, 0, 0, 0.3)'
                //             },
                //             label: {
                //                 position: 'right',
                //                 formatter:'{b}',
                //                 textStyle:{
                //                     color: '#333'
                //                 }
                //             },
                //             force: {  //排斥力的大小
                //                 repulsion: 1000,
                //                 edgeLength:10,
                //                 gravity:0.1,
                //                 layoutAnimation:true
                //             },
                //             lineStyle:{
                //                 color:'source',
                //                 curveness: 0.3
                //             },
                //             emphasis: {
                //                 lineStyle: {
                //                     width: 10
                //                 }
                //             }
                //         }
                //     ]
                // };
                option = {
                    title: {
                        text: 'Knowledge Graph',
                        subtext: 'Author & Affiliation & Research Fields',
                        top: 'bottom',
                        left: 'right'
                    },
                    tooltip: {},
                    legend: [{
                        data: graph.categories.map(function (a) {
                            return a.name;
                        })
                    }],
                    animationDurationUpdate: 1500,
                    animationEasingUpdate: 'quinticInOut',
                    series: [
                        {
                            name: 'Knowledge Graph',
                            type: 'graph',
                            layout: 'circular',
                            circular: {
                                rotateLabel: true
                            },
                            data: graph.nodes,
                            links: graph.links,
                            categories: graph.categories,
                            roam: true,
                            focusNodeAdjacency:true,
                            label: {
                                position: 'right',
                                formatter: '{b}'
                            },
                            lineStyle: {
                                color: 'source',
                                curveness: 0.3,
                                width:2
                            },
                            emphasis: {
                                lineStyle: {
                                    width: 10
                                }
                            }
                        }
                    ]
                };

                myChart.hideLoading();
                myChart.setOption(option);

            },
            error: function (res) {
                alert(res);
            }
        })

    }
})