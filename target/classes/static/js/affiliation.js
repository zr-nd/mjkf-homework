$(document).ready(function () {
    var name=localStorage.getItem("name");
    var authors=localStorage.getItem("authors");
    var papers=localStorage.getItem("papers");
    var citations=localStorage.getItem("citations");
    var authorName=JSON.parse(localStorage.getItem("authorName"));
    $("#one").text(name);
    $("#two").text(name+" has "+authors+" authors, "+papers+" papers and "+citations+" citations.");
    $("#three").text("You can get some information about top authors in "+name+" here.");
    $("#four").text("Authors: "+authors);
    $("#five").text("Papers: "+papers);
    $("#six").text("Citations: "+citations);
    var txt="";
    for(var i=0;i<authorName.length;i++){
        txt=txt+"<tr>"+
            "<td class='author'>"+"<a href='authorDetail'>"+authorName[i].author+"</a>"+"</td>"+
            "<td>"+authorName[i].papers+"</td>"+
            "<td>"+authorName[i].citations+"</td>"+
            "</tr>";
    }
    document.getElementById('seven').innerHTML=txt;

    document.getElementById('field').style.width="1000px";
    document.getElementById('field').style.height="600px";
    getField();

    $("#seven").on('click','.author',function(){
        //alert($(this).text());
        var author=$(this).text();
        var affiliation=$('#one').text();
        $.ajax({
            type:'POST',
            url:'/authorPic/byName',
            async:false,
            data:JSON.stringify({
                'author':author,
                'affiliation':affiliation
            }),
            dataType:'json',
            contentType:'application/json',
            processData:false,
            success:function(res){
                localStorage.setItem("author",res.author);
                localStorage.setItem("affiliation",res.affiliation);
                localStorage.setItem("authorPapers",res.papers);
                localStorage.setItem("refs",res.refs);
                window.location.href="authorDetail";
            },
            error:function(error){
                alert(error);
            }
        })

    })//以上为获取作者画像部分代码

    function getField(){
        $.ajax({
            type:'POST',
            url : '/affiliationField',
            asnyc:true,
            data:JSON.stringify(name),
            contentType: 'application/json',
            processData:false,
            success:function (res) {

                var fieldList = res.map(function (item) {
                    return item.authorKeyWord;
                });
                var activeList = res.map(function (item) {
                    return item.active;
                });
                var field=[];
                for(var i=0;i<fieldList.length;i++){
                    var tmp={
                        value:activeList[i],
                        name:fieldList[i]
                    };
                    field.push(tmp);
                }

                var option = {
                    title:{
                        text:'研究方向',
                        left:'center'
                    },
                    tooltip:{
                        trigger:'item',
                        formatter:'{a}<br/>{b}:{c}({d}%)'
                    },

                    toolbox:{
                        show:true,
                        feature:{
                            mark:{show:true},
                            dataView:{snow:true,readOnly:true},
                            magicType:{
                                show:true,
                                type:['pie','funnel']
                            },
                            restore:{show:true},
                            saveAsImage:{snow:true}
                        }
                    },
                    series:[{
                        type:'pie',
                        name:'活跃度',
                        radius:[20,250],

                        roseType:'radius',
                        avoidLabelOverlap:false,
                        label:{
                            show:false,
                        },
                        emphasis:{
                            label:{
                                show:true
                            }
                        },
                        data:field
                    }]
                };
                var fieldChart = echarts.init($("#field")[0]);
                fieldChart.setOption(option);
            },
            error:function (res) {
                alert(res);
            }
        })
    }
})