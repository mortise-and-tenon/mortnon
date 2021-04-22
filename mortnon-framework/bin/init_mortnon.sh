#! /bin/shell

#======================================================================
# 创建项目脚本
# 提供groupId artifactId version
# 创建一个干净的mortnon项目，此版本为mac os版，需要依赖gsed
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
echo "替换groupId:${mortnonGroup}为${groupId}"
gsed -i "s/${sedMortnonGroupId}/${sedGroupId}/g" `grep -rl "<groupId>${mortnonGroup}</groupId>"  * --col`
# 替换artifactId
echo "替换artifactId:${mortnonArtifact}为${artifactId}"
gsed -i "s/${mortnonArtifact}/${artifactId}/g" `grep -rl "<artifactId>${mortnonArtifact}-"  * --col`
# 替换包名
echo "替换包名${mortnonGroup}.${mortnonArtifact}为${groupId}.${artifactId}"
gsed -i "s/${sedMortnonGroupId}\.${mortnonArtifact}/${sedGroupId}\.${artifactId}/g" `grep -rl "${mortnonGroup}.${mortnonArtifact}"  * --col`
# 替换groupId:artifact格式的
gsed -i "s/${sedMortnonGroupId}\:${mortnonArtifact}/${sedGroupId}\:${artifactId}/g" `grep -rl "${mortnonGroup}:${mortnonArtifact}"  * --col`
# 替换版本号
echo "替换版本号${mortnonVersion}为${version}"
gsed -i "s/${sedMortnonVersion}/${sedVersion}/g" `grep -rl "<version>${mortnonVersion}</version>"  * --col`
# 扫尾
gsed -i "s/${mortnonArtifact}/${artifactId}/g" `grep -rl "${mortnonArtifact}-"  * --col`

echo "=================文件内容替换完成================="
echo "==================开始修改文件夹=================="
echo "当前目录:"`pwd`
sourceDir="${mortnonGroup/.//}/${mortnonArtifact}"
rootDir="${sourceDir%%/*}"
targetDir="${groupId/.//}/${artifactId}"
codeDir="src/main/java"
echo $targetDir

# web模块
echo "mkdir -p ${mortnonArtifact}-web/${codeDir}/${targetDir}"
mkdir -p ${mortnonArtifact}-web/${codeDir}/${targetDir}
echo "mv ${mortnonArtifact}-web/${codeDir}/${sourceDir}/* ${mortnonArtifact}/${artifactId}-web/${codeDir}/${targetDir}"
mv ${mortnonArtifact}-web/${codeDir}/${sourceDir}/* ${mortnonArtifact}-web/${codeDir}/${targetDir}
echo "rm -rf ${mortnonArtifact}-web/${codeDir}/${rootDir}"
rm -rf ${mortnonArtifact}-web/${codeDir}/${rootDir}

# boot模块
echo "mkdir -p ${mortnonArtifact}-boot/${codeDir}/${targetDir}"
mkdir -p ${mortnonArtifact}-boot/${codeDir}/${targetDir}
echo "mv ${mortnonArtifact}-boot/${codeDir}/${sourceDir}/* ${mortnonArtifact}/${artifactId}-boot/${codeDir}/${targetDir}"
mv ${mortnonArtifact}-boot/${codeDir}/${sourceDir}/* ${mortnonArtifact}-boot/${codeDir}/${targetDir}
echo "rm -rf ${mortnonArtifact}-boot/${codeDir}/${rootDir}"
rm -rf ${mortnonArtifact}-boot/${codeDir}/${rootDir}

# dal模块
echo "mkdir -p ${mortnonArtifact}-dal/${codeDir}/${targetDir}"
mkdir -p ${mortnonArtifact}-dal/${codeDir}/${targetDir}
echo "mv ${mortnonArtifact}-dal/${codeDir}/${sourceDir}/* ${mortnonArtifact}/${artifactId}-dal/${codeDir}/${targetDir}"
mv ${mortnonArtifact}-dal/${codeDir}/${sourceDir}/* ${mortnonArtifact}-dal/${codeDir}/${targetDir}
echo "rm -rf ${mortnonArtifact}-dal/${codeDir}/${rootDir}"
rm -rf ${mortnonArtifact}-dal/${codeDir}/${rootDir}

# utils模块
echo "mkdir -p ${mortnonArtifact}-utils/${codeDir}/${targetDir}"
mkdir -p ${mortnonArtifact}-utils/${codeDir}/${targetDir}
echo "mv ${mortnonArtifact}-utils/${codeDir}/${sourceDir}/* ${mortnonArtifact}/${artifactId}-utils/${codeDir}/${targetDir}"
mv ${mortnonArtifact}-utils/${codeDir}/${sourceDir}/* ${mortnonArtifact}-utils/${codeDir}/${targetDir}
echo "rm -rf ${mortnonArtifact}-utils/${codeDir}/${rootDir}"
rm -rf ${mortnonArtifact}-utils/${codeDir}/${rootDir}

# service模块
echo "mkdir -p ${mortnonArtifact}-service/${codeDir}/${targetDir}"
mkdir -p ${mortnonArtifact}-service/${codeDir}/${targetDir}
echo "mv ${mortnonArtifact}-service/${codeDir}/${sourceDir}/* ${mortnonArtifact}/${artifactId}-service/${codeDir}/${targetDir}"
mv ${mortnonArtifact}-service/${codeDir}/${sourceDir}/* ${mortnonArtifact}-service/${codeDir}/${targetDir}
echo "rm -rf ${mortnonArtifact}-service/${codeDir}/${rootDir}"
rm -rf ${mortnonArtifact}-service/${codeDir}/${rootDir}

cd ..
echo "mv ./${mortnonArtifact}/${mortnonArtifact}-boot ./${mortnonArtifact}/${artifactId}-boot"
mv ./${mortnonArtifact}/${mortnonArtifact}-boot ./${mortnonArtifact}/${artifactId}-boot
echo "mv ./${mortnonArtifact}/${mortnonArtifact}-dal ./${mortnonArtifact}/${artifactId}-dal"
mv ./${mortnonArtifact}/${mortnonArtifact}-dal ./${mortnonArtifact}/${artifactId}-dal
echo "mv ./${mortnonArtifact}/${mortnonArtifact}-assembly ./${mortnonArtifact}/${artifactId}-assembly"
mv ./${mortnonArtifact}/${mortnonArtifact}-assembly ./${mortnonArtifact}/${artifactId}-assembly
echo "mv ./${mortnonArtifact}/${mortnonArtifact}-framework ./${mortnonArtifact}/${artifactId}-framework"
mv ./${mortnonArtifact}/${mortnonArtifact}-framework ./${mortnonArtifact}/${artifactId}-framework
echo "mv ./${mortnonArtifact}/${mortnonArtifact}-service ./${mortnonArtifact}/${artifactId}-service"
mv ./${mortnonArtifact}/${mortnonArtifact}-service ./${mortnonArtifact}/${artifactId}-service
echo "mv ./${mortnonArtifact}/${mortnonArtifact}-web ./${mortnonArtifact}/${artifactId}-web"
mv ./${mortnonArtifact}/${mortnonArtifact}-web ./${mortnonArtifact}/${artifactId}-web
echo "mv ./${mortnonArtifact}/${mortnonArtifact}-biz ./${mortnonArtifact}/${artifactId}-biz"
mv ./${mortnonArtifact}/${mortnonArtifact}-biz ./${mortnonArtifact}/${artifactId}-biz
echo "mv ./${mortnonArtifact}/${mortnonArtifact}-utils ./${mortnonArtifact}/${artifactId}-utils"
mv ./${mortnonArtifact}/${mortnonArtifact}-utils ./${mortnonArtifact}/${artifactId}-utils

echo "mv ./${mortnonArtifact} ./${artifactId}"
mv ./${mortnonArtifact} ./${artifactId}

echo "完成项目创建"

