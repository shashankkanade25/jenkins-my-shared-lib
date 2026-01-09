def call(String credId, String imageName) {
  withCredentials([usernamePassword(
    credentialsId: credId,
    usernameVariable: 'DOCKER_USER',
    passwordVariable: 'DOCKER_PASS'
  )]) {
    sh '''
      set -e
        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
        docker tag "$imageName" "$DOCKER_USER/$IMAGE_NAME"
        docker push "$DOCKER_USER/$IMAGE_NAME:latest"
        docker logout
    '''
  }
}
