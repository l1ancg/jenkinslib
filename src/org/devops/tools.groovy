package org.devops

// 打印内容
def PrintMsg(content) {
    PrintMsg(content, 'black')
}

def PrintMsg(content, color) {
    colors = [
        'black'  : "\033[30m ${content} \033[0m",
        'red'    : "\033[31m >>>>>${content}<<<<< \033[0m",
        'green'  : "\033[32m >>>>>${content}<<<<< \033[0m",
        'yellow' : "\033[33m >>>>>${content}<<<<< \033[0m",
        'blue'   : "\033[34m >>>>>${content}<<<<< \033[0m",
        ]
    ansiColor('xterm') {
        println(colors[color])
    }
}