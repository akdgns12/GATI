import * as React from 'react';

const ImageList2 = () => {
  const imgContainer = {
    flex:'none',
    border:'1px solid black',
    height:'100px',
    width:'100px',
    margin:'2px'
  }
  
  const imgItems = []
  
  return (
    <div
      style={{
        display:'flex',
        flexWrap:'wrap',
        height:'100vh',
        width:'100vw',
        justifyContent:'flex-start',
        alignContent:"flex-start",
      }}>
        {imgItems.map((item) => (
        <imgListItem key={item.id}>
          <img
            src={`${item.img}?w=164&h=164&fit=crop&auto=format`}
            srcSet={`${item.img}?w=164&h=164&fit=crop&auto=format&dpr=2 2x`}
            alt={item.title}
            loading="사진을 로딩중입니다."
          />
        </imgListItem>
      ))}
        <div style={imgContainer}></div>
    </div>
)}
export default ImageList2