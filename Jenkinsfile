pipeline {
    agent any
    stages {
        stage('Start Selenium Grid') {
            steps {
                echo "Starting Selenium Grid with Docker Compose..."
                bat 'docker-compose up -d'
                echo "Waiting for Selenium Grid to be ready..."
                bat 'timeout /t 10' // Wait for services to initialize
            }
        }
        stage('Run Tests') {
            steps {
                echo "Building and Running Tests..."
                script {
                    try {
                        bat 'docker-compose exec tests mvn clean install'
                    } catch (Exception e) {
                        echo "Build or test execution failed: ${e.message}"
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }
    }
    post {
        always {
            echo "Stopping Selenium Grid..."
            bat 'docker-compose down'
        }
    }
}
