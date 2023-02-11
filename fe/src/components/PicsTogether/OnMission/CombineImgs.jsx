import React, { useRef, useState, useEffect } from 'react';
import { useSelector } from 'react-redux';

const CombineImgs = () => {
  const imgList = useSelector(state=>{return state.picsTg.getMission}).missionImageDtos
  console.log(imgList)
  const canvasRef = useRef(null);
  const [images, setImages] = useState([]);

  useEffect(() => {
    const imagePromises = []
    imgList.map(obj => {
      imagePromises.push(
        new Promise((resolve) => {
          const image = new Image();
          image.src = obj.img;
          image.onload = () => {
            resolve(image);
          };
        }),
      )
    })

    Promise.all(imagePromises).then((images) => {
      console.log('images',images)
      setImages(images);
    });
  }, []);

  useEffect(() => {
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
  }, [images]);


  // const dataURL = canvasRef.current.toDataURL('image/jpeg');
  // console.log('dataURL',dataURL)


  return (
    <>
      <canvas style={{ backgroundColor: '#F5F5F5', borderRadius:'16px' }} ref={canvasRef} width={300} height={300} />
    </>
  );
};

export default CombineImgs;