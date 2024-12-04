pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning the Git repository'
                git branch: 'master', url: 'https://github.com/Annamalai-Tu/AccuMexs.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Running the build process'
                        bat 'mvn clean install'
            }
        }
        stage('Report') {
            steps {
                echo 'Archiving test reports'
                archiveArtifacts artifacts: 'ExtentReports/**/*.zip', allowEmptyArchive: true
            }
        }
    }
    post {
        always {
            echo 'Cleaning up workspace'
            cleanWs() // Cleans the workspace after the pipeline run
        }
        success {
            echo 'Build succeeded! Sending success email...'
            emailext(
                subject: "Build Succeeded: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Good news! The Jenkins build for the job '${env.JOB_NAME}' with build number #${env.BUILD_NUMBER} succeeded. Check the details at ${env.BUILD_URL}.",
                to: 'annamalai.raja@testunity.com'
            )
        }
        failure {
            echo 'Build failed! Sending failure email...'
            emailext(
                subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Unfortunately, the Jenkins build for the job '${env.JOB_NAME}' with build number #${env.BUILD_NUMBER} failed. Please review the logs at ${env.BUILD_URL}.",
                to: 'annamalai.raja@testunity.com'
            )
        }
    }
}
