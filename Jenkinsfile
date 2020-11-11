pipeline {
 agent any
 tools {
    gradle "gradle"
 }
 stages {
    stage("build") {
         steps {
             bat 'echo building'
         }
    }
     stage("test") {
         steps {
             bat 'echo testing'
         }
     }
     stage("deploy") {
         steps {
             echo "deploying..."
         }
     }
 }
}
