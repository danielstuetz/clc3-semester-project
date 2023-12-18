# clc3-semester-project Dynatrace Monitoring

## Group Members

Jakob Mayr  
Daniel Stütz


## Proposal

### Building a simple application to run with Kubernetes:
**B**ody**M**ass**I**ndex (BMI) Calculator using the producer-consumer-pattern operating in two different containtertypes:
- Producer: Generating artificial bodymass and bodysize data.
- Consumer: Calculating the BMI based on the producer-data.
Using some kind of queue for the data transfer between the containers.

### Setting up Kubernetes 
- Setting up the cluster using google cloud.
- Spawning multible pods for each container type (producer/consumer).

### Integrating Dynatrace-monitoring into Kubernetes
- Requesting a Dynatrace trial.
- Setting up monitoring for the Kubernetes cluster.


### Testing and monitoring the application:
Setting up the monitoring with the Dynatrace trial and produce different logs and errors:
- Generating log warnings (e.g. too low/high BMI).
- Generating errors (e.g. dividing by zero).
- Getting the environment unhealthy (e.g. too many producers).
- Discovering the dynatrace-monitoring application (e.g. where to find the logs, hardware metrics, pod information, etc.)



## Milestones
- Building the application
- Setting up clusters
- Integrating Dynatrace-monitoring
- Testing the whole cycle


## Responsibilities
- Building the application: Daniel
- Containerizing application: Daniel
- Configure Google Cloud access: Jakob
- Setting up Kubernetes: Jakob
- Integrating Dynatrace-monitoring: Both
- Testing: Both
- (Creating) Presentation: Both
