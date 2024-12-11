pipeline {
    agent any
  environment {
          GRID_DIR = 'grid' // Directory where hub.sh and node.sh are located
          HUB_LOG = 'grid/hub.log' // Path to hub log file
          NODE_LOG = 'grid/node.log' // Path to node log file
      }

      stage('Checkout Code') {
                  steps {
                      echo 'Cloning the Git repository...'
                      git branch: 'master', url: 'https://github.com/Annamalai-Tu/AccuMexs.git'
                  }
              }

      stages {
          stage('Maven Clean') {
              steps {
                  script {
                      echo "Running Maven clean..."
                      sh 'mvn clean'
                  }
              }
          }

          stage('Set Execution Permissions') {
              steps {
                  script {
                      echo "Setting execution permissions for hub.sh and node.sh..."
                      cd grid
                      bat "chmod +x ${GRID_DIR}/hub.bat"
                      bat "chmod +x ${GRID_DIR}/node.bat"
                  }
              }
          }

          stage('Start Selenium Hub') {
              steps {
                  script {
                      echo "Starting Selenium Hub using hub.sh..."
                      dir("${GRID_DIR}") {
                          sh "nohup ./hub.sh > hub.log 2>&1 &"
                      }
                      echo "Waiting for 'Started Selenium Hub' message in hub.log..."
                      waitForMessage('Started Selenium Hub', HUB_LOG)
                  }
              }
          }

          stage('Extract IP Address') {
              steps {
                  script {
                      echo "Extracting IP address from hub.log..."
                      def ipAddress = sh(script: "grep -oP '(?<=http://)[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+' ${HUB_LOG}", returnStdout: true).trim()
                      echo "Extracted IP Address - ${ipAddress}"
                      env.IP_ADDRESS = ipAddress
                  }
              }
          }


        stage('Run Tests') {
            steps {
                echo "Building and Running Tests..."
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    bat "mvn clean install"
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
