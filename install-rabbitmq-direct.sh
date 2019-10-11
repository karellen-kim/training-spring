if brew ls --versions rabbitmq > /dev/null; then
  echo "rabbitmq is already installed"
else
    echo "installing rabbitmq..."
    brew install rabbitmq
    export PATH=$PATH:/usr/local/opt/rabbitmq/sbin
fi
rabbitmq-server
