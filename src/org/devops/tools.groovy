package org.devops

// 打印内容
def PrintMsg(content) {
    PrintMsg(content, 'blue')
}

def PrintMsg(content, color) {
    colors = [
        'red'   : "\003[40;31m >>>>>>>>>>${content}<<<<<<<<<< \033[0m",
        'blue'  : "\003[47;34m ${content} \033[0m",
        'green' : "·[1:32m >>>>>>>>>>${content}<<<<<<<<<<·[m",
        'green2' : "\033[40;32m >>>>>>>>>>${content}<<<<<<<<<< \033[0m",
        ]
    ansiColor('xterm') {
        println(colors[color])
    }
}