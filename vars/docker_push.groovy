def call(String credId , String imageName){
  withCredentials([usernamePassword(
                    credentialsId: "${credId}",
                    usernameVariable: 'dockerHubUser',
                    passwordVariable: 'dockerHubPassword'
                )]) {
                    
                        sh "docker login -u ${env.dockerHubUSer} -p ${env.dockerHubPassword}"
                        sh "docker image tag ${imageName} ${env.dockerHubUSer}/${imageName}"
                        sh "docker push ${env.dockerHubUSer}/${imageName}:latest"
                        sh "docker logout"
                  
                } 
}
