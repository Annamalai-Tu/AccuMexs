pipeline {
    agent any

    parameters {
            string(name: 'SECTION_HEADER', defaultValue: 'AccuMexs Testing Pipeline', description: '--- AccuMexs ---')
            booleanParam(name: 'SkipTests', defaultValue: false, description: 'Check to skip tests')
        }

    environment {
        GIT_URL = 'https://github.com/Annamalai-Tu/AccuMexs.git'
        BRANCH_NAME = 'master'
        EMAIL_RECIPIENTS = 'annamalai.raja@testunity.com'
        REPORT_PATH = 'ExtentReports/**/*.zip'
    }

    stages {
        stage('Prepare') {
            steps {
                echo 'Setting up the environment...'
                script {
                    if (params.SkipTests) {
                        currentBuild.result = 'SUCCESS'
                        echo 'Tests skipped as per user input'
                        currentBuild.displayName = "Tests Skipped"
                        return
                    }
                }
            }
        }

        stage('Checkout Code') {
            steps {
                echo 'Cloning the Git repository...'
                git branch: env.BRANCH_NAME, url: env.GIT_URL
            }
        }

        stage('Build and Test') {
            when {
                expression { !params.SkipTests }
            }
            steps {
                echo "Building and Running Tests..."
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Archive Reports') {
            steps {
                echo 'Archiving test reports...'
                archiveArtifacts artifacts: env.REPORT_PATH, allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            deleteDir()
        }

        success {
            echo 'Build completed successfully!'
            mail(
                to: env.EMAIL_RECIPIENTS,
                subject: "Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    Hi Team,

                    The build completed successfully.
                    Job: ${env.JOB_NAME}
                    Build Number: ${env.BUILD_NUMBER}
                    Git Branch: ${env.BRANCH_NAME}
                """,
                attachLog: true, // Attach the console output log
                attachmentsPattern: env.REPORT_PATH // Attach the report file(s)
            )
        }

        failure {
            echo 'Build failed!'
            mail(
                to: env.EMAIL_RECIPIENTS,
                subject: "Build Failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    Hi Team,

                    The build failed.
                    Job: ${env.JOB_NAME}
                    Build Number: ${env.BUILD_NUMBER}
                    Git Branch: ${env.BRANCH_NAME}
                """,
                attachLog: true, // Attach the console output log
                attachmentsPattern: env.REPORT_PATH // Attach the report file(s)
            )
        }
    }
}
