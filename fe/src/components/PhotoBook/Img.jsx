import * as React from 'react';
import { Link } from 'react-router-dom';

const Img = ({item}) => {
  const url = "/photobook/" + item.id

  return (
    <Link to={url}>
      <div
        style={{
          width:'126px',
          height:'126px',
          padding:'2px'
        }}>
        <img
          style={{
            width:'100%',
            height:'100%',
            objectFit:'cover'
          }}
          src={item.img}
          alt={item.title}
          loading="사진을 로딩중입니다."
          />
      </div>   
    </Link>
  )
}
export default Img