import React, { useRef, useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { asyncPostMission } from '../../../../store/PicsTogether/picsTg';

const CombinedImg = () => {
  console.log('combinedImg 실행')
  const dispatch = useDispatch();
  
  const id = useSelector(state => {return state.picsTg.getMission}).id
  const imgList = useSelector(state=>{return state.picsTg.getMission}).missionImageDtos
  const baseUrl = 'https://ggati.site'
  const newImgList = imgList.map(dto=> {return {src : baseUrl + dto.img}} )

  const canvasRef = useRef(null);

  useEffect(() => {
    let images = []
    // Load the images and create an Image object for each image
    const promises = newImgList.map(image => {
      return new Promise(resolve => {
        const img = new Image();
        img.onload = () => {
          images.push(img);
          resolve();
        };
        img.src = image.src;
      });
    });

    // 대안) canvas 데이터 url 형식으로 받는 방법
    // 주의) The canvasRef.current will only have a value after the canvas element has been rendered, which occurs after the component has been mounted.
    // const dataURL = canvasRef.current.toDataURL('image/jpeg');
    // console.log('dataURL',dataURL)
    
    // 합쳐진 이미지 파일 formData 형식으로 미션 완료 axios 보내기
    canvasRef.current.toBlob((blob) => {
      const formData = new FormData();
      formData.append('id', id);
      formData.append('file', blob, 'combined-image.jpg');
      dispatch(asyncPostMission(formData))
    Promise.all(promises).then(() => {
      // Once all the images have loaded, we can draw them on the canvas
      const ctx = canvasRef.current.getContext('2d');
      const imageWidth = 150;
      const imageHeight = 150;
      const spacing = 10;  // Add a spacing of 10 pixels between images
      const numRows = Math.ceil(images.length / 2);
      canvasRef.current.width = Math.max(350, 2 * imageWidth + spacing);
      canvasRef.current.height = Math.max(350, numRows * imageHeight + spacing);
      const xStart = (canvasRef.current.width - (2 * imageWidth + spacing)) / 2;
      let yStart = (canvasRef.current.height - (numRows * imageHeight + spacing)) / 2;
      images.forEach((image, index) => {
        let xPos = xStart + (index % 2) * (imageWidth + spacing);
        const yPos = yStart + Math.floor(index / 2) * (imageHeight + spacing);
        // center the last image in a row when the number of images is odd
        if (index === images.length - 1 && images.length % 2 !== 0) {
          xPos = xStart + Math.floor(index / 2) * (imageWidth + spacing) + (canvasRef.current.width - imageWidth) / 2;
        }
        ctx.drawImage(image, xPos, yPos, imageWidth, imageHeight);
      });
  
      })
    })
  }, []);
  

  return (
    <canvas
      style={{ backgroundColor: '#FFFBEB', borderRadius:'16px' }}
      ref={canvasRef}
      width="100%"
      height="100%"
      width="100%"
      height="100%"
    />
  );
};

export default CombinedImg;