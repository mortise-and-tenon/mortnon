#! /bin/shell

#======================================================================
# 创建项目脚本
# 提供groupId artifactId version
# 创建一个干净的mortnon项目
#
# author: dongfangzan
# date: 2021-04-22
#======================================================================
# groupId
groupId=$1
# artifactId
artifactId=$2
# version
version=$3
# mortnon地址
repoUrl="https://gitee.com/mortise-and-tenon/mortnon.git"
# mortnon 名称
mortnonArtifact="mortnon"
# mortnon group
mortnonGroup="org.mt"
# mortnon 版本
mortnonVersion="0.0.1"


if [ -z "$groupId" ] || [ -z "$artifactId" ] || [ -z "$version" ]; then
  echo "参数错误，请按init_mortnon.sh [grouopId] [artifactId] [version]的格式创建项目"
  exit
fi

echo "开始创建项目"
echo "${groupId}:${artifactId}:${version}"

if [ ! -d "mortnon" ]; then
    git clone ${repoUrl}
    cd "mortnon"
else
  cd "mortnon"
  git pull
fi
# 替换特殊字符
sedGroupId=${groupId//\./\\\.}
sedMortnonGroupId=${mortnonGroup//\./\\\.}
sedMortnonVersion=${mortnonVersion//\./\\\.}
sedVersion=${version//\./\\\.}
# 替换maven文件中的内容
# 替换groupId
gsed -i "s/${sedMortnonGroupId}/${sedGroupId}/g" `grep -rl "<groupId>${mortnonGroup}</groupId>"  * --col`
# 替换artifactId
gsed -i "s/${mortnonArtifact}/${artifactId}/g" `grep -rl "<artifactId>${mortnonArtifact}-"  * --col`
# 替换包名
gsed -i "s/${sedMortnonGroupId}\.${mortnonArtifact}/${sedGroupId}\.${artifactId}/g" `grep -rl "${mortnonGroup}.${mortnonArtifact}"  * --col`
# 替换groupId:artifact格式的
gsed -i "s/${sedMortnonGroupId}\:${mortnonArtifact}/${sedGroupId}\:${artifactId}/g" `grep -rl "${mortnonGroup}:${mortnonArtifact}"  * --col`
# 替换版本号
gsed -i "s/${sedMortnonVersion}/${sedVersion}/g" `grep -rl "<version>${mortnonVersion}</version>"  * --col`
# 扫尾
gsed -i "s/${mortnonArtifact}/${artifactId}/g" `grep -rl "${mortnonArtifact}-"  * --col`

echo "=================文件内容替换完成================="
echo "当前目录:"`pwd`
echo "==================开始修改文件夹=================="

