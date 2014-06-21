var lastMessageTime;
var lastMessageId;
var numberLoadMessages = 10;
function sendMessage() {
    var text = $('#messageInput').val();
    $.ajax({
            type: "POST",
            url: additional_path + '/messages/send/' + dialog_id,
            data: "text=" + text,
            success: function (message) {
                $('#messageInput').val('');
                addMessageToBottom(message);
                scrollMessagesToBottom();
            },
            error: function (error) {
                console.log("sendMessage - " + JSON.stringify(error));
                alert("sendMessage - ERROR");
            }
        }
    );
};

function messageInputKeyDown(event) {
    if (event.keyCode == 13 && event.shiftKey) {
        sendMessage();
    }
};

function addMessageToBottom(message) {
    if (message) {
        var messagesList = $('#messages_list');
        var template = $('#messageTemplate').clone(true, true);

        var data = {
            message: message
        };
        var newMessage = Mustache.render(template.html(), data);
        messagesList.append(newMessage);

        initMessageElementParams(message);
    }
};

function addMessageToTop(message) {
    if (message) {
        var messagesList = $('#messages_list');
        var template = $('#messageTemplate').clone(true, true);

        var data = {
            message: message
        };
        var newMessage = Mustache.render(template.html(), data);
        messagesList.prepend(newMessage);

        initMessageElementParams(message);
    }
};

function initMessageElementParams(message) {
    if (message.new) {
        var messageElem = $('#message_' + message.id);
        messageElem.addClass("new_message");

        messageElem.mouseleave(function () {
            messagesReaded(message.id);
        });
    }
};

function messagesReaded(id) {
    var messageElem = $('#message_' + id);
    if (messageElem.hasClass("new_message")) {
        $.ajax({
                type: "GET",
                url: additional_path + '/notification/messageRevised?id=' + id,
                success: function () {
                    messageElem.removeClass("new_message");
                    messageElem.mouseleave(undefined);
                    updateNotificationsCounts();
                },
                error: function (error) {
                    console.log("messagesReaded - " + JSON.stringify(error));
                    alert("messagesReaded - ERROR");
                }
            }
        );
    }
};

function loadMessagesList(isFirst) {
    $.ajax({
            type: "GET",
            url: additional_path + '/conversations/' + conversation_id + '/getMessages',
            data: "time=" + lastMessageTime + "&number=" + numberLoadMessages,
            success: function (messagesList) {
                var size = messagesList.length;
                for (var ind = size - 1; ind >= 0; ind--) {
                    var message = messagesList[ind];
                    if (message.id != lastMessageId) {
                        addMessageToTop(message);
                        lastMessageTime = message.formatedCreationTime;
                    }
                }
                if (isFirst) {
                    scrollMessagesToBottom();
                }
            },
            error: function (error) {
                console.log("loadMessagesList - " + JSON.stringify(error));
                alert("loadMessagesList - ERROR");
            }
        }
    )
    ;
}
;


function messageListScrolled() {
    var messagesList = $('#messages_list');
    if (messagesList.scrollTop() == 0) {
        loadMessagesList(false);
        messagesList.scrollTop(5);
    }
};

function scrollMessagesToBottom() {
    var messagesList = $('#messages_list');
    messagesList.scrollTop(messagesList.prop("scrollHeight"));
};


