/**
 * 提交回复
 */
function post() {
    let questionId = $("#question_id").val()
    let content = $("#comment_content").val()
    commentToTarget(questionId, 1, content)
}

function commentToTarget(targetId, type, content) {
    if(!content) {
        alert("不能回复空内容")
        return
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if(response.code == 200) {
                window.location.reload()
            }else {
                if(response.code == 2003) {
                    let isAccepted = confirm(response.message)
                    if(isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=84837829286ebeb5709b&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", "true")
                    }
                }
                alert(response.message)
            }
            console.log(response)
        },
        dataType: "json"
    })
}

function comment(e) {
    let commentId = e.getAttribute("data-id")
    let content = $("#input-" + commentId).val()
    commentToTarget(commentId, 2, content)
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    let id = e.getAttribute("data-id")
    let comment = $("#comment-"+id)

    let collapse = e.getAttribute("data-collapse")
    if(collapse) {
        comment.removeClass("in")
        e.removeAttribute("data-collapse")
        e.classList.remove("active")
    }else {
        let subCommentContainer = $("#comment-" + id)
        if(subCommentContainer.children().length != 1) {
            comment.addClass("in")
            e.setAttribute("data-collapse", "in")
            e.classList.add("active")
        }else {
            $.getJSON("/comment/" + id, (data) => {
                $.each(data.data.reverse(), (index, comment) => {
                    let avatarElement = null
                    if(comment.user.avatarUri) {
                        avatarElement = $("<img/>", {
                            "class": "media-object img-rounded",
                            "src": comment.user.avatarUri
                        })
                    }else {
                        avatarElement = $("<img/>", {
                            "class": "media-object img-rounded",
                            "src": "/image/log.jpg"
                        })
                    }
                    let mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    })

                    let mediaBobyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format("DD-MMMM-YYYY")
                    })))

                    mediaLeftElement.append(avatarElement)
                    let mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBobyElement)
                    let commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement)
                    subCommentContainer.prepend(commentElement)
                })
                comment.addClass("in")
                e.setAttribute("data-collapse", "in")
                e.classList.add("active")
            })
        }
    }
}

function selectTag(e) {
    let value = e.getAttribute("data-tag")
    let previous = $("#tag").val()
    if(previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value)
        } else {
            $("#tag").val(value)
        }
    }
}

function showSelectTag() {
    $("#select-tag").show()
}
