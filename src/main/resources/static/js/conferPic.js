$(document).ready(function () {
    var id = localStorage.getItem('id');
    var title = localStorage.getItem('title');
    var paperCount = localStorage.getItem('paperCount');
    var referCount = localStorage.getItem('referCount');
    document.getElementById("confer-name").innerText=title;
    document.getElementById('paperCount').innerText = paperCount;
    document.getElementById('referCount').innerText = referCount;


    getAuthorByConferName();  //先获取作者
    getPaperByConferName();

    var authorsCount;

    function getAuthorByConferName() {
        $.ajax({
            type: 'POST',
            url: '/getAuthorByConferName',
            async: true,
            data: JSON.stringify(title),
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                viewAuthors(res);
            }
        })
    }

    function getPaperByConferName() {
        $.ajax({
            type: 'POST',
            url: '/getPaperByConferName',
            async: true,
            data: JSON.stringify(title),
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                viewPapers(res);
            }
        })
    }


    function viewPapers(list) {
        var text = "";
        for(var i=0;i<list.length;i++){
            text = text +
               "<li class='item-box'>"+
                "<div class='paper-title'>"+ list[i].title +"</div>"+
                "<div class='paper-referCount'>"+ "<span>"+"Reference Count: " +"</span>" +list[i].referenceCount + "</div>" +
                "<div class='paper-authors'>"+ list[i].authors +"</div>"+
                "<div class='paper-affiliations'>"+ list[i].affiliations +"</div>"+
                "<div class='paper-abs'>"+ "<a target='_blank' href="+ list[i].pdfLink+">"+ list[i].paperAbstract +"</a>"+"</div>"+
                "<div class='paper-keyword'>"+ "<div class='keyword-title'>"+"Keywords: "+ "</div>" +
                list[i].authorKeyWord +"</div>"
            +"</li>"
        }
        document.getElementById('paper-list-content').innerHTML = text;
    }

    function viewAuthors(list) {
        var text = "";
        for(var i=0;i< list.length;i++){
            text = text +
                "<tr>" +
               "<td class='author-item'>"+ "<a href='authorDetail'>"+list[i].author +"</a>" +"</td>"+
                "<td class='count-item'>"+list[i].papers + "</td>"+
                "<td class='affiliation-item'>"+"<a href='affiliationDetail'>"+list[i].affiliation +"</a>" + "</td>"+
                "</tr>"
        }
        document.getElementById('author-list-content').innerHTML = text;
    }

    $('#author-list-content').on('click','.author-item',function(){
        var author=$(this).text();
        var affiliation=$(this).next().next().text();
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
    })

    $('#author-list-content').on('click','.affiliation-item',function(){
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

