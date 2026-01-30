pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Build Project'
            }
        }
        stage("Run UTs"){
            steps {
                echo 'run unit Tests'
           }
       }
       stage("Deploy to dev"){
            steps {
                echo "Deploy to Dev"
            }
       }
       stage('Run automation Tests'){
           steps {
               echo "Run automation"
           }
       }
    }

}
