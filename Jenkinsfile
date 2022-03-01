@Library('jenkinslib')

def tools = new org.devops.tools()

String workspace = "/opt/jenkins/workspace"

pipeline {
    // agent any
    agent {
        node {
            label "master"                 // 指定工作节点(在manage nodes and clouds设置节点标签)
            customWorkspace "${workspace}" // 指定运行工作目录
        }
    }

    options {
        timestamps()                      // 日志会有时间(插件Timestamper)
        skipDefaultCheckout()             // 删除隐式checkout scm语句
        disableConcurrentBuilds()         // 禁止并行
        timeout(time:1, unit: 'HOURS')    // 流水线超时设置1h
        retry(1)                          // 失败重试次数
    }

    environment {
        env_var = '环境变量'
    }

    parameters {
        gitParameter(
            name: 'BRANCH_TAG',
            type: 'PT_BRANCH_TAG',
            defaultValue: 'master',
            description: '选择分支',
        )
        choice(
            name: "param_choice_var",
            choices: ['v1', 'v2'],
            description: "选择入参",
        )
        string(
            name: 'param_str_var', 
            defaultValue: 'param_str_dft_val',
            description: '字符串入参',
        )
        booleanParam(
            defaultValue: true,
            name: 'param_bool_var',
            description: '布尔入参',
        )
    }

    stages {
        stage("get-code") { // 阶段名称
            steps { // 步骤
                echo '获取代码'
                script {
                    println(env_var)
                    println("${params.param_str_var}")
                    println("${params.param_bool_var}")
                }
                // checkout([$class: 'GitSCM',
                //           branches: [[name: "${params.param_branch_tag}"]],
                //           doGenerateSubmoduleConfigurations: false,
                //           extensions: [],
                //           gitTool: 'Default',
                //           submoduleCfg: [],
                //           userRemoteConfigs: [[url: 'https://gitee.com/gokins/gokins.git']]
                //         ])
            }
        }
        stage("build") {
            steps {
                timeout(time: 10, unit: "SECONDS") { // 步骤超时时间
                    script {
                        println('应用打包')
                        mvnHome = tool 'm3'
                        println(mvnHome)
                        sh "${mvnHome}/bin/mvn --version"
                    }
                }
            }
        }
        stage("code-scan") {
            when {
                // 判断环境变量（入参）
                environment name: 'param_str_var', value: 'param_str_dft_val'
                // branch 'master'            // 判断分支
                // expression { return true } // 判断表达式返回true时
                // not { branch ''; environment name: '', value: '' }   // 判断取返
                // allOf { branch ''; environment name: '', value: '' } // 全部满足
                // anyOf { branch ''; environment name: '', value: '' } // 任一满足
            }
            steps {
                timeout(time: 10, unit: "SECONDS") {
                    script {
                        println('代码扫描')

                        tools.PrintMsg("print at my lib")
                        tools.PrintMsg("print at my lib with red", "red")
                        tools.PrintMsg("print at my lib with green", "green")
                        tools.PrintMsg("print at my lib with green2", "green2")
                    }
                }
            }
        }
        stage("after-code-scan") {
            failFast true
            parallel { // 并行执行
                stage('send-report') {
                    steps { // 执行内容
                        echo '发送扫描报告'
                    }
                }
                stage('save-report') {
                    steps {
                        echo '保存扫描报告'   
                    }
                }
            }
        }
    }

    post {
        always { // 总是执行
            script {
                println("always")
            }
        }
        success { // 成功后执行
            script {
                // currentBuild：一个全局的变量
                // description：构建的描述
                currentBuild.description = "\n 构建成功"
            }
        }
        failure { // 失败后执行
            script {
                currentBuild.description = "\n 构建失败"
            }
        }
        aborted { // 取消后执行
            script {
                currentBuild.description = "\n 构建取消"
            }
        }
    }
}
