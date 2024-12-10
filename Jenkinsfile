pipeline {
    agent any
    stages {
        stage('Start Selenium Grid') {
            steps {
                echo "Starting Selenium Grid with Docker Compose..."
                bat 'docker-compose up -d'
            }
        }
        stage('Run Tests') {
            steps {
                echo "Running Tests..."
                bat 'docker-compose exec tests mvn test'
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
