node {
    stage('Checkout'){
        checkout scm
    }
    stage('Build'){
        //sh './mvnw -B -DskipTests clean package'
        sh 'echo Preparing docker build...'
        docker.build("kecampcr/localizacion:${TAG_NAME}").push()
    }
}