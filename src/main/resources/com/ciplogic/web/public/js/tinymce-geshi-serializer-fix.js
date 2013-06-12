function postProcess(wnd, data) {
    var match,
        content,
        geshiTagRegexp = /<pre .*?xml:lang=.*?>.*?<\/pre>/gi;

    content = data.content;

    while (match = geshiTagRegexp.exec(content)) {
        geshiTagRegexp.lastIndex = match.index + 1;

        content = content.substr(0, match.index) +
                  match[0].replace(/&amp;/g, "&") +
                  content.substr(match.index + match[0].length);
    }

    data.content = content;
}

tinyMCE.editors[0].serializer.onPostProcess.add(function(wnd, data) {
    return postProcess(wnd, data);
});