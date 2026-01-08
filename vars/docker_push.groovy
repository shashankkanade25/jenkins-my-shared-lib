def call(){
  withCredentials([usernamePassword(
                    credentialsId: 'dockerHub',
                    usernameVariable: 'dockerHubUser',
                    passwordVariable: 'dockerHubPassword'
                )]) {
                    sh '''
                        echo $dockerHubPassword | docker login -u $dockerHubUser --password-stdin
                        docker tag shashankkanade25/node-todo-test:latest $dockerHubUser/node-todo-test:latest
                        docker push $dockerHubUser/node-todo-test:latest
                        docker logout
                    '''
                } 
}
