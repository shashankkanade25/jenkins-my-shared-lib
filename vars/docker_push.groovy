def call(String credId , String imageName){
  withCredentials([usernamePassword(
                    credentialsId: "${credId}",
                    usernameVariable: 'dockerHubUser',
                    passwordVariable: 'dockerHubPassword'
                )]) {
                    
                        sh "docker login -u "$DOCKER_USER" --password-stdin"   
                        sh "docker image tag ${imageName} ${env.dockerHubUSer}/${imageName}"
                        sh "docker push ${env.dockerHubUser}/${imageName}:latest"
                        sh "docker logout"
                  
                } 
}
