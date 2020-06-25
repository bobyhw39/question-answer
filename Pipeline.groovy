def repo = 'https://github.com/bobyhw39/question-answer.git'
def credentialsId = "git"
def dockerDev = [:]
def dockerDevHost = "10.10.11.15"
def dockerRegistry = "10.10.11.6:8083"
def imageTag = "qustionanswer"
def imageName = "qustionanswer"
def imageVersion = "latest"
def appEnvVariableName = "QUESTIONANSWER_IMAGES"
def appEnvVariable = dockerRegistry + "/" + imageTag + "/" + imageName + ":" + imageVersion
def images = imageName + ":" + imageVersion


pipeline {
    agent any
    stages {
        stage('git checkout'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: credentialsId, url: repo ]]])
            }
        }
        stage('build and deploy'){
            steps{
                sh 'mvn clean package'
                sh 'mvn deploy'
            }
        }
        stage('docker build and tag image'){
            steps{
                sh 'docker build . -t ${images}'
                sh 'docker tag ${imageName}:${imageVersion} ${appEnvVariable}'
            }
        }
        stage('Push Images'){
            steps{
                withCredentials(
                        [usernamePassword(
                                credentialsId: 'nexus-docker',
                                usernameVariable: 'USERNAME',
                                passwordVariable: 'PASSWORD'
                        )]
                ){
                    sh " docker login -u ${USERNAME} -p ${PASSWORD} http://${dockerRegistry}"
                    sh "docker push ${appEnvVariable}"
                }
            }
        }

    }
}
