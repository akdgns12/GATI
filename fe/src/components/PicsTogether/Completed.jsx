import * as React from 'react';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
import ListSubheader from '@mui/material/ListSubheader';
import IconButton from '@mui/material/IconButton';
import InfoIcon from '@mui/icons-material/Info';
import PicsTgExampleDinner from '../../static/PicsTgExampleDinner.png'
import PicsTgExampleSky from '../../static/PicsTgExampleSky.png'
import { Box } from '@mui/material';

// AXIOS 연결 전 테스트용
const MissionImgs = [
  {
    title:'오늘 저녁은 무엇인가요?',
    img : PicsTgExampleSky
  },
  {
    title:'오늘 하늘은 어땠나요?',
    img : PicsTgExampleDinner
  },
  {
    title:'짱구',
    img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjplK5Iw7kiaLK5XX1g5VJwc3W8m92UjVRgw&usqp=CAU",
  },
  {
    title:'짱구2',
    img: "https://media.bunjang.co.kr/product/188410848_1_1653044293_w360.jpg",
  },
  {
    title:'짱구3',
    img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFqRXeZ9Fqf0LJOyjSISoJkVXG26pxpRTji6ANAQ98iYYsJavoIgRu8xjMqSKfFBf1U9Y&usqp=CAU",
  },
]

export default function Completed() {
  return (
    <Box
      sx={{
        display:'flex',
        flexWrap:'wrap',
        height:'100vh'
      }}>
      <ImageList
        sx={{
          width: 500,
          height: 450,
          '-ms-overflow-style': 'none',  /* IE and Edge */
          'scrollbar-width': 'none',  /* Firefox */
          '&::-webkit-scrollbar': {display: 'none'} /* Chrome */
          }}>
        {MissionImgs.map((item) => (
          <ImageListItem key={item.img}>
            <img
              src={`${item.img}?w=248&fit=crop&auto=format`}
              srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
              alt={item.title}
              loading="로딩중.."
            />
            <ImageListItemBar
              title={item.title}
              subtitle={item.author}
            />
          </ImageListItem>
        ))}
      </ImageList>
    </Box>
  );
}
