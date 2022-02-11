$(document).ready(function () {
    var author=localStorage.getItem("author");
    var affiliation=localStorage.getItem("affiliation");
    var papers=localStorage.getItem("authorPapers");
    var refs=localStorage.getItem("refs");
    var authorKeyWords="";
    //var authorName=JSON.parse(localStorage.getItem("authorName"));
    $("#one").text(author);
    document.getElementById('two').innerHTML="<a href='affiliationDetail'>"+affiliation+"</a>";
    $("#three").text(author+" has "+papers+" papers and "+refs+" citations.");
    $("#four").text("Research Fields: ");
    $("#five").text("Papers: "+papers);
    $("#six").text("Citations: "+refs);

    var allKeyWords;
    var paperList;
    function makeAuthorPapers(){
        $.ajax({
            type:'POST',
            url:'/author/authorPapers',
            async:true,
            data:JSON.stringify({
                'author':author,
                'affiliation':affiliation
            }),
            dataType:'json',
            contentType:'application/json',
            processData:false,
            success:function(res){
              showAuthorPapers(res);
              paperList=res;
              allKeyWords=getAllKeyWords(res);
              for( var i=0;i<allKeyWords.length;i++){
                  authorKeyWords=authorKeyWords+"<span class='field'>"+"<a href='researchField'>"+
                      allKeyWords[i]+"</a>"+"</span>"+"; ";
              }
              document.getElementById('four').innerHTML="Research Fields: "+authorKeyWords;
            },
            error:function(error){
                alert("错误信息: "+error);
            }
            }
        )
    }

    function showAuthorPapers(list){
        var text="";
        for(var i=0;i<list.length;i++){
            var temp="";
            temp =temp+ "<div class='information' style='width:auto;font-size:14px'>"+
                "<ul>"+
                "<li class='title'>" + list[i].title + "</li>"+
                //"<li>" + list[i].authors + "</li>"+
                "<li>" + list[i].publicTitle + "</li>"+
                //"<li class='affiliation'>"+"<a href='#'>"+list[i].affiliations+"</a>"+"</li>"+
                "<li>"+"Year:"+list[i].year+"</li>"+
                "<li class='detail'>"+"<a href='detail'>"+"Detail"+"</a>"+"</li>"+
                "</ul>"+
                "</div>";
            text=text+"<tr>"+
                "<td style='width:50%'>"+temp+"</td>"+
                "<td>"+list[i].referenceCount+"</td>"+
                "</tr>";
        }
        document.getElementById('seven').innerHTML=text;
    }


    function getAllKeyWords(list){
        var temp="";
        for(var i=0;i<list.length-1;i++){
            temp=temp+list[i].authorKeyWord+";";
        }
        temp+=list[list.length-1].authorKeyWord;
        //alert(temp);
        var arr=temp.split(", ").join(";").split(";");
        var arr1=[];
        for(var i=0;i<arr.length;i++){
            if(arr1.indexOf(arr[i])==-1&&arr[i]!=""){
                arr1.push(arr[i]);
            }
        }
        return arr1;
    }
    makeAuthorPapers();

    $("#seven").on('click','.detail',function(){
        var title=$(this).prev().prev().prev().text();
        //alert(title);
        for(var i=0;i<paperList.length;i++){
            if(title===paperList[i].title){
                localStorage.setItem('title',paperList[i].title);
                localStorage.setItem('authors',paperList[i].authors);
                localStorage.setItem('affiliations',paperList[i].affiliations);
                localStorage.setItem('publicTitle',paperList[i].publicTitle);
                localStorage.setItem('year',paperList[i].year);
                localStorage.setItem('startPage',paperList[i].startPage);
                localStorage.setItem('endPage',paperList[i].endPage);
                localStorage.setItem('pdfLink',paperList[i].pdfLink);
                localStorage.setItem('authorKeyWord',paperList[i].authorKeyWord);
                localStorage.setItem('referenceCount',paperList[i].referenceCount);
                localStorage.setItem('publisher',paperList[i].publisher);
                localStorage.setItem('docID',paperList[i].docID);
                localStorage.setItem('paperAbstract',paperList[i].paperAbstract);
                break;
            }
        }
        window.location.href="detail";
        //alert(allKeyWords);
    })

    $("#four").on('click','.field',function(){
        var keyword=$(this).text();
        //alert(keyword);
        $.ajax({
            type: 'POST',
            url: '/researchPortray',
            async: false,
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
        //alert(localStorage.getItem('authorKeyWord'));
    })

    $("#two").on('click',function(){
        var affiliation=$(this).text();
        $.ajax({
            type: 'POST',
            url: '/affiliation',
            async: false,
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


})