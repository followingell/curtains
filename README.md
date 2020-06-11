# Curtains
🌅 Receive a customised SMS every morning using Twilio, Amazon CloudWatch Events & AWS Lambda.

[![DepShield Badge](https://depshield.sonatype.org/badges/followingell/curtains/depshield.svg)](https://depshield.github.io) ![GitHub](https://img.shields.io/github/license/followingell/curtains)

## Motivation
Waking up, I would frequently get distracted, checking other apps after I'd looked at the weather on my phone. Since this app uses SMS, I can still stay informed about the day and prevent distraction from happening by keeping my phone offline until I'm ready to start the day.

## Example
![Example Output](https://github.com/followingell/curtains/images/example.png)

## Features
- **Weather Reporting:** *actual temperature, weather condition, humidity, precipitation, wind & sunset time.*
- **News:** *today's top headline with link to story & historic headline with link to story.*
- **Affirmation:** *An affirmation.*

Weather and news are customisable. App design allows for new components to be added easily.

## Source Contents
The project source includes function code and supporting resources:

- `CurtainsFunction/src/main` - A Java function.
- `CurtainsFunction/src/test` - Unit tests.
- `CurtainsFunction/pom.xml` - A Maven build file.

Use the following instructions to deploy the application.

## Usage

### Requirements
- [An AWS account](https://aws.amazon.com/free).
- [A Twilio account](https://www.twilio.com/try-twilio).
- [A News API API Key](https://newsapi.org/docs/get-started).
- [A The Guardian Open Platform API Key](https://open-platform.theguardian.com/access/).
- [A Java 11 runtime environment](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/what-is-corretto-11.html).
- [Maven 3](https://maven.apache.org/docs/history.html).
- [Amazon CLI](https://aws.amazon.com/cli/).

### Guide

```java

```
## To Do
- Implement unit tests for functionality that uses environment variables.

## Acknowledgments
- **Code & Walkthrough:** A big thank you to [Matthew Gilliard](https://github.com/mjg123) for his post: [_'Wake up to Useful Daily Messages with Java and Azure Functions'_](https://www.twilio.com/blog/wake-up-daily-messages-java-azure-functions).

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Unlicense](https://choosealicense.com/licenses/unlicense/)

## Project Status
- This project is currently not under active development.