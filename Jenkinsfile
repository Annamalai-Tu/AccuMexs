pipeline {
    agent any
    stages {
        stage('Start Selenium Grid') {
            steps {
                echo "Starting Selenium Grid with Docker Compose..."
                bat 'docker-compose up -d'
                echo "Waiting for Selenium Grid to be ready..."
                bat 'timeout /t 10' // Adjust as per your environment
            }
        }

        stage('Checkout Code') {
            steps {
                echo 'Cloning the Git repository...'
                git branch: 'master', url: 'https://github.com/Annamalai-Tu/AccuMexs.git'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Building and Running Tests..."
                script {
                    try {
                        bat 'mvn clean install'
                    } catch (Exception e) {
                        echo "Build or test execution failed: ${e.message}"
                        error "Pipeline terminated due to test failures."
                    }
                }
            }
        }
    }
    post {
        always {
            echo "Cleaning up Selenium Grid..."
            bat 'docker-compose down'
        }
    }
}
