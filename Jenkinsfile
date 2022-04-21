node () {
    stage('Checkout'){
        checkout scm
    }
    stage('Build'){
        sh "cat DockerFile"
        //sh './mvnw -B -DskipTests clean package'
        sh 'echo Preparing docker build...'
        sh 'docker build -t kecampcr/localizacion:latest .'
    }
}
