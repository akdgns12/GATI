import * as React from 'react';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';

export default function StandardImageList() {
  return (
    <ImageList sx={{ width: 500, height: 450 }} cols={3} rowHeight={164}>
      {itemData.map((item) => (
        <ImageListItem key={item.img}>
          <img
            src={`${item.img}?w=164&h=164&fit=crop&auto=format`}
            srcSet={`${item.img}?w=164&h=164&fit=crop&auto=format&dpr=2 2x`}
            alt={item.title}
            loading="lazy"
          />
        </ImageListItem>
      ))}
    </ImageList>
  );
}

const zzangoo =
  {
    img: 'https://upload.wikimedia.org/wikipedia/ko/thumb/4/4a/%EC%8B%A0%EC%A7%B1%EA%B5%AC.png/230px-%EC%8B%A0%EC%A7%B1%EA%B5%AC.png',
    title: 'zzangoo',
  }

const itemData = [
  zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,zzangoo,
];