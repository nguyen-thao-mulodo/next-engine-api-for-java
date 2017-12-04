# プロジェクトのセットアップ・実行 

Vagrantをインストールしてクライアント情報を変更した後、ターミナルを開いてプロジェクトがあるフォルダーへ移動し、
__`vagrant up`__コマンドを使用してプロジェクトのセットアップと実行を開始します。

仮想マシーンの設定は__`Vagrantfile`__  ファイルに規則されます。  

仮想マシーンがダウンロードされ、開発環境がセットアップされますので初回はかなり時間がかかる可能性あります。
完了した後、__`ne_java_tutorial`__ プロジェクトは__`https://localhost:8443`__  のURLで実行されます。


 * __`receive_order_search`__ボタンを押して__receiveorder_baseを検索する__機能を使用します。  

 * __`upload master_goods`__ボタンを押して __.csvファイルをアップロードする__機能を使用します。  

 * 上記２つ機能を使用する際、__認証__機能によってユーザがnext-engineアカウントにログインするよう催促されます。 

 * __ログイン（トークン）認証の更新・確認バッチ__機能はバックグラウンドで実行してユーザのログイン状態を維持します。 

File __`Vagrantfile` :__
```
# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure(2) do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://atlas.hashicorp.com/search.
  config.vm.box = "edrw/centos7-64"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  config.vm.network "forwarded_port", guest: 8443, host_ip: "127.0.0.1", host: 8443

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  config.vm.synced_folder ".", "/vagrant", mount_options: ['dmode=777','fmode=777']

  config.vm.provider "virtualbox" do |vb|
    vb.name = "ne_java_tutorial"
  end

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  #
  config.vm.provider "virtualbox" do |vb|
  #   # Display the VirtualBox GUI when booting the machine
  #   vb.gui = true
  #
  #   # Customize the amount of memory on the VM:
  #   vb.memory = "1024"
    vb.customize ["modifyvm", :id, "--memory", "1024"]
    vb.customize ["modifyvm", :id, "--natdnsproxy1", "off"]
    vb.customize ["modifyvm", :id, "--natdnshostresolver1", "off"]
  end

  ##################################
  # Inline shell setup
  ##################################
  config.vm.provision "shell", inline: <<-SHELL

echo 'UPDATE YUM'
timedatectl set-timezone Asia/Tokyo
yum update -y
yum upgrade -y
yum install -y wget

echo 'INSTALL JAVA'
yum install -y java-1.8.0-openjdk-devel

echo 'CREATE KEY'
source /home/vagrant/.bash_profile
cd /vagrant
if [ "$(ls | grep 'keystore.p12')" != "keystore.p12" ]; then
keytool -genkey -alias tomcat \
-storetype PKCS12 -keyalg RSA -keysize 2048 \
-keystore keystore.p12 -validity 3650 << EOF
123456
123456
devel
devel
Hamee
Tokyo


y
EOF

chown -R vagrant:vagrant keystore.p12
fi

# --- MySQL 5.6.*(http://ips.nekotype.com/3420/)
echo 'INSTALL MYSQL'
cd /home/vagrant
wget http://repo.mysql.com/mysql-community-release-el6-5.noarch.rpm
rpm -ivh mysql-community-release-el6-5.noarch.rpm
yum install -y mysql-server
# --- Date -> MySQL date config
sed "s/\[mysqld\]/[mysqld]\ndefault-time-zone='+9:00'/" /etc/my.cnf -i
/etc/init.d/mysqld start

mysql -uroot < /vagrant/vagrant/script.sql

echo 'SETUP TIMEZONE'
cp /usr/share/zoneinfo/Japan /etc/localtime
sh -c "echo 'ZONE=\"Asia/Tokyo\"' > /etc/sysconfig/clock"
yum install -y ntpdate.x86_64
ntpdate ntp.nict.jp
  SHELL
  config.vm.provision "shell", inline: "cd /vagrant && ./gradlew --stop && ./gradlew bootRun", run: 'always'
end

```