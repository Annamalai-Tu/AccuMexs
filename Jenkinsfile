pipeline {
    agent any
    stages {
        stage('Start Selenium Grid') {
            steps {
                echo "Starting Selenium Grid with Docker Compose..."
                sh 'docker-compose up -d'
            }
        }
        stage('Run Tests') {
            steps {
                echo "Running Tests..."
                sh 'docker-compose exec tests mvn test'
            }
        }
    }
    post {
        always {
            echo "Stopping Selenium Grid..."
            sh 'docker-compose down'
        }
    }
}
