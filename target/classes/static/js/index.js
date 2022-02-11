$(document).ready(function () {
    /*主页4个TOP展示*/

    TopAuthorPic();
    TopAffiliation();
    TopConference();
    TopField();

    // hotSearchShow();

    var TopConferenceList;

    function TopConference() {
        $.ajax({
            type:'POST',
            url:'/conferPic',
            async:true,
            contentType: 'application/json',
            processData: false,
            success:function (res) {
                viewTopConfer(res);
                TopConferenceList = res;
            },
            error:function (error) {
                alert(error);
            }
        })
    }

    function viewTopConfer(list) {

        var text = "";
        for(var i =0;i<list.length;i++){
            text = text +
                "<tr class='confer-name'>"+
                "<td style='max-width:50px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;'"+" title='"+list[i].name+"'><a class='left' href='#'>"+list[i].name+"</a></td>"+
                "<td class='right-number'>"+"<div class='right'>" +list[i].paperCount +"</div>" +"</td>" +
                "</tr>";
        }
        document.getElementById('top-confer-content').innerHTML = text;

    }

    $("#top-confer-content").on('click','.left',function () {
        var title = $(this).text();
        //alert(title);
        for(var i =0;i<TopConferenceList.length;i++){
            if(TopConferenceList[i].name == title){
                localStorage.setItem('Id',TopConferenceList[i].id);
                localStorage.setItem('title',title);
                localStorage.setItem('paperCount',TopConferenceList[i].paperCount);
                localStorage.setItem('referCount',TopConferenceList[i].referCount);
            }
        }
        window.location.href = "conferPicPage";
    })

    function TopField(){
        $.ajax({
            type: 'POST',
            url: '/popField',
            async: true,
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                var txt="";
                for(var i=0;i<res.length;i++){
                    txt=txt+"<tr>"+
                        "<td class='field' "+"style='max-width:50px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;' "+"title='"+res[i].authorKeyWord+"'>"+res[i].authorKeyWord+"</td>"+
                        "<td class='right-number'>"+res[i].active.toFixed(1)+"</td>"+
                        "</tr>";

                }
                document.getElementById('nine').innerHTML=txt;
            },
            error: function (res) {
                alert(res);
            }
        })
    }

    $("#nine").on('click','.field',function(){
        var keyword=$(this).text();
        $.ajax({
            type: 'POST',
            url: '/researchPortray',
            async: true,
            data: JSON.stringify(keyword),
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                localStorage.setItem('authorKeyWord',res.keyword);
                localStorage.setItem('author_count',res.author_count);
                localStorage.setItem('paper_count',res.paper_count);
                localStorage.setItem('reference_count',res.reference_count);

                window.location.href="researchField";
            },
            error: function (error) {
                alert("failure" + error);
            }
        })
    })

    function TopAffiliation(){

        var txt="";
        $.ajax({
            type: 'POST',
            url: '/index1',
            async: true,
            data: JSON.stringify(txt),
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                for(var i=0;i<res.length;i++){
                    txt=txt+"<tr>"+
                        "<td class='affiliation' "+"style='max-width:50px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;' "+"title='"+res[i].name+"'>"+res[i].name+"</td>"+
                        "<td class='right-number'>"+res[i].active.toFixed(1)+"</td>"+
                        "</tr>";

                }
                document.getElementById('eight').innerHTML=txt;

            },
            error: function (error) {
                alert("failure" + error);
            }
        })
    }
    $("#eight").on('click','.affiliation',function(){
        var affiliation=$(this).text();
        $.ajax({
            type: 'POST',
            url: '/affiliation',
            async: true,
            data: JSON.stringify(affiliation),
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                localStorage.setItem('name',res.name);
                localStorage.setItem('authors',res.authors);
                localStorage.setItem('papers',res.papers);
                localStorage.setItem('citations',res.citations);
                localStorage.setItem('authorName',JSON.stringify(res.authorName));
                window.location.href="affiliationDetail";
            },
            error: function (error) {
                alert("failure" + error);
            }
        })

    })

    //以下为yy添加部分
    var authorPicRes;
    function TopAuthorPic(){
        $.ajax({
            type:'POST',
            url:'/authorPic',
            async:true,
            data:"",
            contentType:'json',
            processData:false,
            success:function(res){
                //alert("成功");
                view(res);
                authorPicRes=res;
            },
            error:function(error){
                alert(error);
            }
        })
    }
    function view(list) {

        var text="";
        for(var i=0;i<list.length;i++){
            text =text+ "<tr>"+
                "<td class='author'>"+list[i].author+"</td>"+
                "<td class='right-number'>"+(list[i].papers*0.7+list[i].refs*0.3).toFixed(1)+"</td>"+
                "</tr>";

        }

        document.getElementById('seven').innerHTML=text;//搜索新标签的时候应该重新显示，而不是在原来结果的后面append
    }



    $("#seven").on('click','.author',function(){
        //alert($(this).text());
        for(var i=0;i<authorPicRes.length;i++){
            if($(this).text()==authorPicRes[i].author && $(this).next().text()==(authorPicRes[i].papers*0.7+authorPicRes[i].refs*0.3).toFixed(1)){
                localStorage.setItem("author",authorPicRes[i].author);
                localStorage.setItem("affiliation",authorPicRes[i].affiliation);
                localStorage.setItem("authorPapers",authorPicRes[i].papers);
                localStorage.setItem("refs",authorPicRes[i].refs);
                break;
                //alert(authorPicRes[i].refs);
            }
        }
        window.location.href="authorDetail";
    })//以上为获取作者画像部分代码

    // document.getElementById('paper-referCount').style.width="520px";
    // document.getElementById('paper-referCount').style.height="450px";
    // document.getElementById('authorMost').style.width="550px";
    // document.getElementById('authorMost').style.height="450px";
    // document.getElementById('conference').style.width="520px";
    // document.getElementById('conference').style.height="450px";
    //
    // var axisLabel1 = {
    //     // 方法2：换行显示
    //     interval: 0, //强制全部显示，1表示隔一个；2隔两个
    //     formatter: function (params) {
    //         var newParamsName = ""; // 最终拼接成的字符串
    //         var paramsNameNumber = params.length; // 实际标签的个数
    //         var provideNumber = 6; // 每行能显示的字的个数
    //         var rowNumber = Math.ceil(paramsNameNumber / provideNumber); // 换行的话，需要显示几行，向上取整
    //         /**
    //          * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
    //          */
    //         // 条件等同于rowNumber>1
    //         if (paramsNameNumber > provideNumber) {
    //             /** 循环每一行,p表示行 */
    //             for (var p = 0; p < rowNumber; p++) {
    //                 var tempStr = ""; // 表示每一次截取的字符串
    //                 var start = p * provideNumber; // 开始截取的位置
    //                 var end = start + provideNumber; // 结束截取的位置
    //                 // 此处特殊处理最后一行的索引值
    //                 if (p == rowNumber - 1) {
    //                     // 最后一次不换行
    //                     tempStr = params.substring(start, paramsNameNumber);
    //                 } else {
    //                     // 每一次拼接字符串并换行
    //                     tempStr = params.substring(start, end) + "\n";
    //                 }
    //                 newParamsName += tempStr; // 最终拼成的字符串
    //             }
    //
    //         } else {
    //             // 将旧标签的值赋给新标签
    //             newParamsName = params;
    //         }
    //         //将最终的字符串返回
    //         return newParamsName;
    //     },
    // };
    //
    // function makePaperRef(){
    //     $.ajax({
    //         type:'POST',
    //         url : '/referenceCount',
    //         asnyc:true,
    //         contentType: 'application/json',
    //         processData:false,
    //         success:function (res) {
    //             //alert("res:"+ res[0].referenceCount);
    //             var tableData = res.map(function (item) {
    //                 return item.referenceCount;
    //             });
    //             var nameList = res.map(function (item) {
    //                 return item.title;
    //             });
    //
    //             var option = {
    //                 label:{
    //                     show:true,//开启显示
    //                     position:'top'
    //                 },
    //                 tooltip:{
    //                     trigger:'axis',
    //                     axisPointer:{type:'shadow'}
    //                 },
    //                 grid:{
    //                     y2:200,
    //                     left:"12%", //距离边框左部
    //                     bottom:"20%" //距离边框底部
    //                 },
    //
    //                 title : {
    //                     text: '被引用次数最多的前十篇论文',
    //                     textStyle:{
    //                         fontWeight: 'bold',
    //                         color: '#e4a777'
    //                     },
    //                     x:'center'
    //                 },
    //
    //                 xAxis:{
    //                     type:'category',
    //                     // data:nameList,
    //                     data:nameList.reverse(),
    //                     axisLabel : {
    //                         formatter: function(){
    //                             return "";
    //                         }
    //                     }
    //                 },
    //                 yAxis:{
    //                     type:'value'
    //                 },
    //                 series:[{
    //                     // data:tableData,
    //                     data:tableData.reverse(),
    //                     type:'bar',
    //                     name:'被引用数',
    //                     barGap:40
    //                 }]
    //             };
    //             // var referCountChart = echarts.init($("#paper-referCount")[0]);
    //             var referCountChart = echarts.init(document.getElementById("paper-referCount"));
    //             referCountChart.setOption(option);
    //         },
    //         error:function (res) {
    //             alert(res);
    //         }
    //     })
    // }
    //
    // function makeAuthor(){
    //     $.ajax({
    //         type:'POST',
    //         url : '/author',
    //         asnyc:true,
    //         contentType: 'application/json',
    //         processData:false,
    //         success:function (res) {
    //
    //             var nameList = res.map(function (item) {
    //                 return item.name;
    //             });
    //             var countList = res.map(function (item) {
    //                 return item.count;
    //             });
    //             var option = {
    //                 label:{
    //                     show:true,//开启显示
    //                     position:'top'
    //                 },
    //                 grid:{
    //                     y2:200,
    //                     left:"12%", //距离边框左部
    //                     bottom:"20%" //距离边框底部
    //                 },
    //                 title : {
    //                     text: '发表论文数最多的前十位作者',
    //                     textStyle:{
    //                         fontWeight: 'bold',
    //                         color: '#e4a777'
    //                     },
    //                     x:'center'
    //                 },
    //                 xAxis:{
    //                     type:'category',
    //                     data:nameList,
    //                     axisLabel:axisLabel1
    //                 },
    //                 yAxis:{
    //                     type:'value'
    //                 },
    //                 series:[{
    //                     data:countList,
    //                     type:'bar',
    //                     name:'发表论文数',
    //                     barGap:40
    //                 }]
    //             };
    //             // var authorChart = echarts.init($("#authorMost")[0]);
    //             var authorChart = echarts.init(document.getElementById("authorMost"));
    //             authorChart.setOption(option);
    //         },
    //         error:function (res) {
    //             alert(res);
    //         }
    //     })
    // }
    //
    // function makeConference(){
    //     // var pieCharts = echarts.init($("#conference")[0]);
    //     var pieCharts = echarts.init(document.getElementById("conference"));
    //     pieCharts.showLoading();
    //     $.ajax({
    //         type:'POST',
    //         url:'/conference',
    //         asnyc:true,
    //         contentType: 'application/json',
    //         processData:false,
    //         success:function (res) {
    //             pieCharts.hideLoading();
    //             pieCharts.setOption({
    //                 title:{
    //                     text:'会议统计图',
    //                     subtext:'论文数',
    //                     textStyle:{
    //                         fontWeight: 'bold',
    //                         color: '#e4a777'
    //                     },
    //                     x:'center'
    //                 },
    //                 tooltip:{
    //                     trigger:'item',
    //                     formatter:"{a} <br/>{b} : {c} ({d}%)"
    //                 },
    //                 legend:{
    //                     orient:'vertical',
    //                     x:'left',
    //                     data:[]
    //                 },
    //                 toolbox:{
    //                     show:true,
    //                     feature:{
    //                         mark:{show : true},
    //                         dataView:{show:true, readOnly:false},
    //                         magicType:{
    //                             show:true,
    //                             type:['pie','funnel'],
    //                             option:{
    //                                 funnel:{
    //                                     x:'25%',
    //                                     width:'50%',
    //                                     funnelAlign:'left',
    //                                     max:1548
    //                                 }
    //                             }
    //                         },
    //                         restore:{show:true},
    //                         saveAsImage:{show:true}
    //                     }
    //                 },
    //                 calculable:true,
    //                 series:[{
    //                     name:'论文数',
    //                     type:'pie',
    //                     radius : '55%',
    //                     center:['50%','60%'],
    //                     data:res
    //                 }]
    //             })
    //         },
    //         error:function (res) {
    //             alert(res);
    //         }
    //
    //     })
    // }

    $("#search").click(function () {
        var searchText = $("#write").val();
        if(searchText!==''&&!searchText.match(/^[ ]*$/)) {
            localStorage.setItem('searchText',searchText);
            //getKeyWords(searchText);
            window.location.href="searchRes";
        }
        else{
            alert("搜索内容不能为空");
        }


    })
    // $(document).keydown(function (event) {
    //     if (event.keyCode == 13) { //keyCode=13是回车键
    //         $('#search').triggerHandler('click');
    //     }
    // });
    $("#write").bind('keypress', function (event) {
        if (event.keyCode == "13") {

            var searchText = $("#write").val();
            if(searchText!==''&&!searchText.match(/^[ ]*$/)) {

                localStorage.setItem('searchText',searchText);

                window.location.href="searchRes";

            }
            else{
                alert("搜索内容不能为空");
            }
        }
    })


    // $("#advanced-search").click(function(){
    //     var searchText = $("#write").val();
    //     if(searchText!==''&&!searchText.match(/^[ ]*$/)) {
    //         localStorage.setItem('searchText',searchText);
    //         //getKeyWords(searchText);
    //         window.location.href="searchRes";
    //     }
    //     else{
    //         alert("搜索内容不能为空");
    //     }
    // })

    // function hotSearchShow(){
    //     $(".hot-search-container").show();
    //     var ciyun= '<p style="font-size: 20px;font-weight:bold;color:#e4a777;text-align: center;">'+'关键字词云图'+'</p>'+'<img '+'src='+'"/images/kkp.jpg"'+' id='+'"ciyun"'+' width="100%"'+' height="100%"'+'>';
    //         // '<p style="font-size: 25px;text-align: center;">'+'关键字词云图'+'</p>';
    //
    //     document.getElementById("hot-search-ciyun").innerHTML=ciyun;
    //     makePaperRef();
    //     makeAuthor();
    //     makeConference();  //增加会议的图表
    // }

    /*$("#hot-searches").click(function () { /!*热点展示*!/
        if(count%2==0) {
            count=count+1;
            $("#wrap").hide();
            $("#wrap1").hide();
            $("#wrap2").hide();
            $("#hot-searches").text("返回首页");
            $(".hot-search-container").show();
            var ciyunTitle="以下为各个论文标题中所包含搜索关键字的词云图";
            var ciyun='<img '+'src='+'"/images/kkp.jpg"'+' id='+'"ciyun"'+' width="1000"'+' height="400"'+' alt="ciyun"'+'>';
            document.getElementById("ciyun-title").innerText=ciyunTitle;
            document.getElementById("hot-search-ciyun").innerHTML=ciyun;
            document.getElementById('paper-referCount').style.width = "1200px";
            document.getElementById('paper-referCount').style.height = "600px";
            document.getElementById('authorMost').style.width = "1200px";
            document.getElementById('authorMost').style.height = "600px";
            document.getElementById('conference').style.width = "1200px";
            document.getElementById('conference').style.height = "600px";
            makePaperRef();
            makeAuthor();
            makeConference();  //增加会议的图表
        }
        else{
            count=count+1;
            $("#wrap").show();
            $("#wrap1").show();
            $("#wrap2").show();
            $("#hot-searches").text("热点展示");
            $(".hot-search-container").hide();
        }
    });*/



    // $("#signUp").click(function(e){
    //
    //     e.stopPropagation();
    //     $("#username").val("");
    //     $("#pwd").val("");
    //     $("#filepath").val("");
    //     $("#signForm").show();
    // })
    // $("#btn").click(function(){
    //     var text1=$("#username").val();
    //     var text2=$("#pwd").val();
    //     if(text1===''||text1.match(/^[ ]*$/)||text2==''||text2.match(/^[ ]*$/)){
    //         alert("用户名密码不能为空");
    //     }
    //     else {
    //         var userForm = {
    //             username: $("#username").val(),
    //             password: $("#pwd").val()
    //         }
    //         $.ajax({
    //             type: 'POST',
    //             url: '/login',
    //             async: true,
    //             data: JSON.stringify(userForm),
    //             contentType: 'application/json',
    //             processData: false,
    //             success: function (res) {
    //
    //                 if (res) {
    //                     $("#signForm").hide();
    //                     $("#fileForm").show();
    //                 } else {
    //                     alert("用户名密码错误");
    //                 }
    //             },
    //             error: function (error) {
    //                 alert("failure" + error);
    //             }
    //         })
    //     }
    // })
    //
    // $("#btn1").click(function(){
    //         var filepath=$("#filepath").val();
    //     $.ajax({
    //         type: 'POST',
    //         url: '/updata',
    //         async: true,
    //         data: JSON.stringify(filepath),
    //         contentType: 'application/json',
    //         processData: false,
    //         success: function (res) {
    //
    //             if(res>0){
    //                 alert("成功添加"+res+"条数据");
    //                 $("#signForm").hide();
    //                 $("#fileForm").hide();
    //
    //             }
    //             else if(res==0){
    //                 alert("添加数据全部重复");
    //             }
    //             else{
    //                 alert("文件不存在");
    //             }
    //         },
    //         error: function (error) {
    //             alert("failure" + error);
    //         }
    //     })
    //
    // })
    $(document).click(function(e){
        var pop=$(".Form");
        if(!pop.is(e.target) && pop.has(e.target).length === 0){
            pop.hide();
        }
    })
})