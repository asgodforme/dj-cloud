// 代表整条流水线，包含整条流水线的逻辑
pipeline {
    // 指定流水线的执行位置（Jenkins agent）。流水线的每个阶段都必须在某个地方（物理机，虚拟机，docker容器）执行。agent
    // 部分指具体在哪里执行。
    agent any

    // 设置环境变量
    environment {
        M2_HOME="/var/jenkins_home/apache-maven-3.9.0/"
        PATH="${PATH}:${M2_HOME}/bin"
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10')) // 保存最近历史构建记录的数量。
        // checkoutToSubdirectory('/var/jenkins_home/dir') // Jenkins从版本控制库拉取源码时，默认检出到工作空间的根目录中，此选项可以指定检出到工作空间的子目录中。
        // disableConcurrentBuilds() // 禁止pipeline同时执行
        // newContainerPerStage() // 当agent为docker或dockerfile时，指定在同一个Jenkins节点上，每个stage都分别运行在一个新的容器中，而不是所有stage都运行在同一个容器中
        // retry(4) //当发生失败时进行重试，可以指定整个pipeline的重试次数
        // timeout(time: 10, unit: 'HOURS') // 如果 pipeline 执行时间过长，超出了我们设置的 timeout 时间，Jenkins 将中止pipeline
    }
    // 流水线中有多个stage的容器，stages部分至少包含一个stage.
    stages {
        // 阶段，代表流水线的阶段，每个阶段都必须有名称。
        stage('Build') {
            // 代表阶段中的一个或多个具体的步骤，steps至少包含一个步骤
            steps {
                echo "这是我的第一条流水线"
                sh "mvn clean package"
                sh "printenv" //将环境变量打印到console中
            }
            // 整个pipeline或阶段完成后一些附加的步骤。可选的。
            /**
            • always：不论当前完成状态是什么，都执行。
            • changed：只要当前完成状态与上一次完成状态不同就执行。
            • fixed：上一次完成状态为失败或不稳定（unstable），当前完成状态为成功时执行。
            • regression：上一次完成状态为成功，当前完成状态为失败、不稳定或中止（aborted）时执行。
            • aborted：当前执行结果是中止状态时（一般为人为中止）执行。
            • failure：当前完成状态为失败时执行。
            • success：当前完成状态为成功时执行。
            • unstable：当前完成状态为不稳定时执行。
            • cleanup：清理条件块。不论当前完成状态是什么，在其他所有条件块执行完成后都执行
            */
            post {
                changed {
                    echo "pipeline post changed"
                }
                always {
                    echo "pipeline post always"
                }
                success {
                    echo "pipeline post success"
                }
            }
        }
        stage('script execute') {
            steps {
                script {
                    def browsers = ['chrome', 'firebox']
                    for (int i = 0; i < browsers.size(); ++i) {
                        echo "Testing the ${browsers[i]} browser"
                    }
                }
            }
        }
    }
}