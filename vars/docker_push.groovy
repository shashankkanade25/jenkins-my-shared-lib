def call(String credId, String imageName) {
  withCredentials([usernamePassword(
    credentialsId: credId,
    usernameVariable: 'DOCKER_USER',
    passwordVariable: 'DOCKER_PASS'
  )]) {
    withEnv(["IMAGE_NAME=${imageName}"]) {
      sh '''
        set -e
        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
        docker tag "$IMAGE_NAME" "$DOCKER_USER/$IMAGE_NAME"
        docker push "$DOCKER_USER/$IMAGE_NAME"
        docker logout
      '''
    }
  }
}
