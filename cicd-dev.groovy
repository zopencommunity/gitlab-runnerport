node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/gitlab-runnerport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/gitlab-runnerport.git'), string(name: 'PORT_DESCRIPTION', value: 'GitLab Runner is a tool that allows you to run your CI/CD jobs and send the results back to GitLab.' ), string(name: 'BUILD_LINE', value: 'DEV'), string(name: 'NODE_LABEL', value: "go_120" ) ]
  }
}
